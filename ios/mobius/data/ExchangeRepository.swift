//
//  ExchangeRepository.swift
//  Mobius Test App
//
//  Created by Иван Важнов on 24/03/2018.
//  Copyright © 2018 Revolut. All rights reserved.
//

import Foundation
import common

class ExchangeRepository: common.ExchangeRepository {
    
    private let api: BittrexApi
    
    init(api: BittrexApi) {
        self.api = api
    }
    
    override func getAllMarkets(callback: KotlinContinuation) {
        api.getAllMarkets(callback: callback)
    }
    
    override func getTicker(market: Market, callback: KotlinContinuation) {
        print("getTicker on thread is \(Thread.current)")
        api.getTicker(market: market, callback: callback)
    }
    
}
