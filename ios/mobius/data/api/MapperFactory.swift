//
//  MapperFactory.swift
//  mobius
//
//  Created by Иван Важнов on 25/03/2018.
//  Copyright © 2018 Revolut. All rights reserved.
//

import Foundation
import Alamofire
import SwiftyJSON
import Rev

protocol Mapper {
    associatedtype T
    
    func map(response: DataResponse<Any>) -> T?
}

class MarketsMapper: Mapper {
    
    typealias T = [RevMarket]
    
    func map(response: DataResponse<Any>) -> [RevMarket]? {
        if let result = response.result.value {
            let jsonResult = JSON(result)
            return jsonResult["result"].arrayValue.map({json -> RevMarket in
                return RevMarket(
                    marketCurrency: json["MarketCurrency"].stringValue,
                    marketCurrencyLong: json["MarketCurrencyLong"].stringValue,
                    baseCurrency: json["BaseCurrency"].stringValue,
                    baseCurrencyLong: json["BaseCurrencyLong"].stringValue,
                    minTradeSize: json["MinTradeSize"].doubleValue,
                    marketName: json["MarketName"].stringValue,
                    isActive: json["IsActive"].boolValue,
                    created: json["Created"].stringValue,
                    logoUrl: json["LogoUrl"].string
                )
            })
        } else {
            return nil
        }
    }
}

class TickersMapper: Mapper {
    
    typealias T = RevTicker
    
    func map(response: DataResponse<Any>) -> RevTicker? {
        if let result = response.result.value {
            let jsonResult = JSON(result)
            let dic = jsonResult["result"].dictionaryValue
            return RevTicker(
                bid: dic["Bid"]!.doubleValue,
                ask: dic["Ask"]!.doubleValue,
                last: dic["Last"]!.doubleValue
            )
        } else {
            return nil
        }
    }
}

class MapperFactory {
    
    static let marketsMapper = MarketsMapper()
    
    static let tickersMapper = TickersMapper()
    
}
