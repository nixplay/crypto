//
//  FileMD5Hash.h
//  Crypto
//
//  Created by kumar on 27/10/14.
//  Copyright (c) 2014 nixplay. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface FileMD5Hash : NSObject

+ (NSString *)md5HashOfFileAtPath:(NSString *)filePath;

@end
