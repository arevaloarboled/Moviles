//
//  OneMessage.swift
//  Messages
//
//  Created by Estudiantes on 3/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import Foundation
import JSONHelper
struct SharedFiles:Deserializable
{
    var id: Int?
    var name: String?
    var contentType: String?
    var from: Int?
    var to: Int?
    var date: String?
    init(data: [String: AnyObject]) {
        id <-- data["id"]
        name <-- data["name"]
        contentType <-- data["contentType"]
        from <-- data["from"]
        to <-- data["to"]
        date <-- data["date"]
    }
}