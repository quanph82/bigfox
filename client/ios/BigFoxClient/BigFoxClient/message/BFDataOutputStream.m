//
//  BFDataOutputStream.m
//  BigFoxClient
//
//  Created by QuyenNX on 9/7/15.
//  Copyright (c) 2015 QuyenNX. All rights reserved.
//

#import "BFDataOutputStream.h"

@interface BFDataOutputStream()
{
    NSMutableData *data;
    NSInteger length;
}

@end

@implementation BFDataOutputStream
- (id)init{
    self = [super init];
    if(self != nil){
        data = [[NSMutableData alloc] init];
        length = 0;
    }
    return self;
}

- (void)writeChar:(int8_t)v {
    int8_t ch[1];
    ch[0] = (v & 0x0ff);
    [data appendBytes:ch length:1];
    length++;
}

- (void)writeShort:(int16_t)v {
    int8_t ch[2];
    ch[0] = (v & 0x0ff00)>>8;
    ch[1] = (v & 0x0ff);
    [data appendBytes:ch length:2];
    length = length + 2;
}

- (void)writeInt:(int32_t)v {
    int intValue=[self littleToBig:v];
    [data appendBytes:&intValue length:4];
    length = length + 4;
//    int8_t ch[4];
//    ch[0] = (v >> 24) & 0xff ;
//    ch[1] = (v >> 16) & 0xff;
//    ch[1] = (v >> 8) & 0xff;
//    ch[1] = (v >> 0) & 0xff;
//    [data appendBytes:ch length:4];
//    length = length + 4;
}

- (void)writeLong:(int64_t)v {
    int8_t ch[8];
    for(int32_t i = 0;i<8;i++){
        ch[i] = ((v >> ((7 - i)*8)) & 0x0ff);
    }
    [data appendBytes:ch length:8];
    length = length + 8;
}

- (void)writeUTF:(NSString *)v {
    NSData *d = [v dataUsingEncoding:NSUTF8StringEncoding];
    NSInteger len = [d length];
    [self writeShort:len];
    [data appendData:d];
    length = length + len;
}

- (void)writeBytes:(NSData *)v {
    NSInteger len = [v length];
    [self writeInt:len];
    [data appendBytes:&v length:len];
    length = length + len +4;
}

- (void)writeBytes2:(NSData *)v {
    NSInteger len = [v length];
    [data appendData:v];
    length = length + len;
}

- (void) writeByte:(int)v {
    [data appendBytes:(&v) length:1];
    length++;
}
- (NSData *)toByteArray{
    return [[NSData alloc] initWithData:data];
}

-(void)writeBoolaen:(bool)v
{
    if (v==YES) {
        [self writeChar:1];
    }
    else {
        [self writeChar:0];
    }
}

-(void)writeFloat:(float)v
{
    NSSwappedFloat bigEndianFloat;
    bigEndianFloat=NSSwapHostFloatToBig(v);
    [data appendBytes:&bigEndianFloat length:4];
    length+=4;
}

-(void) writeDouble:(double)v
{
    NSSwappedDouble bigEndianDouble;
    bigEndianDouble=NSSwapHostDoubleToBig(v);
    [data appendBytes:&bigEndianDouble length:8];
    length+=8;
}

-(UInt32)littleToBig:(UInt32) value
{
    return (value>>24)|((value>>8)&0xFF00)|((value<<8)&0xFF0000)|(value<<24);
}
@end
