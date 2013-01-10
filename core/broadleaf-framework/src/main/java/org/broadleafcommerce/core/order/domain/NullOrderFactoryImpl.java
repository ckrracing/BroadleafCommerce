/*
 * Copyright 2012 the original author or authors.
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

package org.broadleafcommerce.core.order.domain;

import org.springframework.stereotype.Service;

/**
 * @see org.broadleafcommerce.core.order.domain.NullOrderFactory
 * @author apazzolini
 */
@Service("blNullOrderFactory")
public class NullOrderFactoryImpl implements NullOrderFactory {
    
    protected static final Order NULL_ORDER = new NullOrderImpl();

    @Override
    public Order getNullOrder() {
        return NULL_ORDER;
    }

}
