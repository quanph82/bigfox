//
//  ICompress.h
//  BigFoxClient
//
//  Created by QuyenNX on 9/4/15.
//  Copyright (c) 2015 QuyenNX. All rights reserved.
//

#import <Foundation/Foundation.h>

@protocol ICompress <NSObject>
- (NSData*) compress : (NSData*) data;
- (NSData*) decompress : (NSData*) data ;
@end
