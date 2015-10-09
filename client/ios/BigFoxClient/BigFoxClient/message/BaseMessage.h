//
//  BaseMessage.h
//  BigFoxClient
//
//  Created by QuyenNX on 9/4/15.
//  Copyright (c) 2015 QuyenNX. All rights reserved.
//

#import <Foundation/Foundation.h>
#define STATUS_CORE 0x01
#define STATUS_ZIP 0x02
#define STATUS_CONTINUE 0x04
@interface BaseMessage : NSObject
@property int length;
@property int tag;
@property int mSequence;
@property int sSequence;
@property int status;
@property int checkSum;
- (id) init : (bool) isCore;
- (int) getLength;
- (void) setLength:(int)length;
- (int) getTag;
- (void) setTag:(int)tag;
- (int) getMSequence;
- (void) setMSequence:(int)mSequence;
- (int) getSSequence;
- (void) setSSequence:(int)sSequence;
- (int) getStatus;
- (void) setStatus:(int)status;
- (int) getCheckSum;
- (void) setCheckSum:(int)checkSum;
- (NSString*) toString;
-(BOOL) isCore;
- (void) execute;

@end