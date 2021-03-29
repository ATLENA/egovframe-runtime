/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package egovframework.brte.sample.testcase.test;

import org.junit.runner.RunWith;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.brte.sample.common.domain.trade.CustomerCredit;

/**
* FilxedLength 방식으로 데이터처리를 수행하는 테스트
* @author 배치실행개발팀
* @since 2012. 06.27
* @version 1.0
* @see
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/egovframework/batch/jobs/fixedLengthIoJob.xml")
public class EgovFixedLengthFunctionalTests extends EgovAbstractIoSampleTests {

	/**
	 * 배치결과를 다시 읽을 때  reader 설정하는 메소드
	 */
	@Override
	protected void pointReaderToOutput(ItemReader<CustomerCredit> reader) {
		JobParameters jobParameters = new JobParametersBuilder(super.getUniqueJobParameters()).addString("inputFile", "file:./target/test-outputs/fixedLengthOutput.txt")
				.toJobParameters();
		StepExecution stepExecution = MetaDataInstanceFactory.createStepExecution(jobParameters);
		StepSynchronizationManager.close();
		StepSynchronizationManager.register(stepExecution);
	}

	/**
	 * 잡파라미터를 설정하기 위한 메소드 
	 * @return jobParameters
	 */
	@Override
	protected JobParameters getUniqueJobParameters() {
		return new JobParametersBuilder(super.getUniqueJobParameters()).addString("inputFile", "/egovframework/data/input/fixedLength.txt")
				.addString("outputFile", "file:./target/test-outputs/fixedLengthOutput.txt").toJobParameters();
	}

}