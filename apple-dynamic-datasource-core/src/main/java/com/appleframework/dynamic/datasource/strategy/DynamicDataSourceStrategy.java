/**
 * Copyright © 2018 organization baomidou
 * <pre>
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
 * <pre/>
 */
package com.appleframework.dynamic.datasource.strategy;

import java.util.List;
import javax.sql.DataSource;

/**
 * The interface of dynamic datasource switch strategy
 *
 * @author TaoYu Kanyuxia
 * @see RandomDynamicDataSourceStrategy
 * @see LoadBalanceDynamicDataSourceStrategy
 * @since 1.0.0
 */
public interface DynamicDataSourceStrategy {

	/**
	 * determine a database from the given dataSources
	 *
	 * @param dataSources given dataSources
	 * @return final dataSource
	 */
	DataSource determineDataSource(List<DataSource> dataSources);
}
