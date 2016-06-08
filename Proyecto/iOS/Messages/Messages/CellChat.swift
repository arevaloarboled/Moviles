//
//  CellChat.swift
//  Messages
//
//  Created by Estudiantes on 4/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit

class CellChat: UITableViewCell {
    
    var message:OneMessage?
    @IBOutlet var texto: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
}