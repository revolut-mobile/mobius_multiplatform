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
import common

protocol Mapper {
    associatedtype T
    
    func map(response: DataResponse<Any>) -> T?
}

class MarketsMapper: Mapper {
    
    typealias T = [Market]
    
    func map(response: DataResponse<Any>) -> [Market]? {
        if let result = response.result.value {
            let jsonResult = JSON(result)
            return jsonResult["result"].arrayValue.map({json -> Market in
                return Market(
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
    
    typealias T = common.Ticker
    
    func map(response: DataResponse<Any>) -> common.Ticker? {
        if let result = response.result.value {
            let jsonResult = JSON(result)
            let dic = jsonResult["result"].dictionaryValue
            return common.Ticker(
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
