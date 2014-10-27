//
//  Crypto.m
//  Crypto
//
//  Created by kumar on 27/10/14.
//  Copyright (c) 2014 nixplay. All rights reserved.
//

#import <Cordova/CDV.h>
#import "Crypto.h"
#import "FileMD5Hash.h"

@implementation Crypto
- (void)md5:(CDVInvokedUrlCommand*)command {
    CDVPluginResult* pluginResult = nil;
    NSString* filePath = [command.arguments objectAtIndex:0];

    if (filePath != nil) {
    	NSString *prefix = @"file://";

        if ([filePath hasPrefix:prefix])
            filePath = [filePath substringFromIndex:[prefix length]];

        filePath = [filePath stringByReplacingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        NSString *fileMD5Hash = [FileMD5Hash md5HashOfFileAtPath:filePath];

        NSLog(@"MD5 hash of file \"%@\" at path:  \"%@\"", filePath, fileMD5Hash);

    	pluginResult = [CDVPluginResult resultWithStatus: CDVCommandStatus_OK messageAsString:fileMD5Hash];
    } else {
    	NSLog(@"Is empty");
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"File name cannot be empty"];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}
@end
