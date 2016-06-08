//
//  Contact.swift
//  Messages
//
//  Created by Estudiantes on 3/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import Foundation
import JSONHelper
struct Contact:Deserializable {
    var userId = 0
    var userName = ""
    var nombre = ""
    init(data: [String: AnyObject]) {
        userId <-- data["userId"]
        userName <-- data["userName"]
        nombre <-- data["nombre"]
    }
}