/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.spark;

import com.facebook.presto.nativeworker.AbstractTestNativeTpchConnectorQueries;
import com.facebook.presto.testing.ExpectedQueryRunner;
import com.facebook.presto.testing.QueryRunner;

public class TestPrestoSparkNativeTpchConnectorQueries
        extends AbstractTestNativeTpchConnectorQueries
{
    @Override
    protected QueryRunner createQueryRunner()
    {
        return PrestoSparkNativeQueryRunnerUtils.createTpchRunner();
    }

    @Override
    protected ExpectedQueryRunner createExpectedQueryRunner()
            throws Exception
    {
        return PrestoSparkNativeQueryRunnerUtils.createJavaQueryRunner();
    }

    @Override
    protected void assertQuery(String sql)
    {
        super.assertQuery(sql);
        PrestoSparkNativeQueryRunnerUtils.assertShuffleMetadata();
    }

    @Override
    public void testMissingTpchConnector()
    {
        super.testMissingTpchConnector(".*Catalog tpch does not exist*");
    }
}
