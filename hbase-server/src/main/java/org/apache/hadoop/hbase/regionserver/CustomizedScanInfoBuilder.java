/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hbase.regionserver;

import org.apache.yetus.audience.InterfaceAudience;

/**
 * Helper class for CP hooks to change max versions and TTL.
 */
@InterfaceAudience.Private
public class CustomizedScanInfoBuilder implements ScanOptions {

  private final ScanInfo scanInfo;

  private Integer maxVersions;

  private Long ttl;

  public CustomizedScanInfoBuilder(ScanInfo scanInfo) {
    this.scanInfo = scanInfo;
  }

  @Override
  public int getMaxVersions() {
    return maxVersions != null ? maxVersions.intValue() : scanInfo.getMaxVersions();
  }

  @Override
  public void setMaxVersions(int maxVersions) {
    this.maxVersions = maxVersions;
  }

  @Override
  public long getTTL() {
    return ttl != null ? ttl.longValue() : scanInfo.getTtl();
  }

  @Override
  public void setTTL(long ttl) {
    this.ttl = ttl;
  }

  public ScanInfo build() {
    if (maxVersions == null && ttl == null) {
      return scanInfo;
    }
    return scanInfo.customize(getMaxVersions(), getTTL());
  }

  @Override
  public String toString() {
    return "ScanOptions [maxVersions=" + getMaxVersions() + ", TTL=" + getTTL() + "]";
  }
}
