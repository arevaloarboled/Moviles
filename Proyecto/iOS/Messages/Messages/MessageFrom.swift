//
//  MessageFrom.swift
//  Messages
//
//  Created by Estudiantes on 3/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import Foundation
import JSONHelper
struct MessageFrom:Deserializable {
    var id=0
    var from=0
    var to=0
    var text=""
    var date=""
    init(data: [String: AnyObject]) {
        id <-- data["id"]
        from <-- data["from"]
        to <-- data["to"]
        text <-- data["text"]
        date <-- data["date"]
    }
}