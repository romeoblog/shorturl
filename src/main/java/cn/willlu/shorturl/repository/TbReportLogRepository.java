/*
 *  Copyright 2019 https://github.com/romeoblog/spring-cloud.git Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package cn.willlu.shorturl.repository;

import cn.willlu.shorturl.entity.TbReportLog;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

/**
 * @author willlu.zheng
 * @date 2019-12-07
 */
public interface TbReportLogRepository extends CrudRepository<TbReportLog, Integer> {

    Long countByIpAndUrlAndMethod(String ip, String url, String method);

    TbReportLog findByIpAndUrlAndMethod(String ip, String url, String method);

}
