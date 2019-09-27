//
//  Assembly.swift
//  Mobius Test App
//
//  Created by Иван Важнов on 25/03/2018.
//  Copyright © 2018 Revolut. All rights reserved.
//

import Foundation
import EasyDi
import SharedCode

class ServiceAssembly: Assembly {


    var exchangeRepository: SharedCode.ExchangeRepository {
        return define(init: ExchangeRepository(dbArgs: DbArgs()))
    }
    
    var exchangeInteractor: AllMarketsTickersInteractor {
        return define(init: AllMarketsTickersSimultaneousInteractor(
            repository: self.exchangeRepository
        ))
    }
        
}
