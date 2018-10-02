#!/usr/bin/python
from __builtin__ import range
import struct
f=open('/var/log/etherCAT/etherCAT.log', 'r')
wf = open("./etherCAT_data/etherCAT.out","w+")
lines=f.readlines()
ax=1
sendStartNo=None
sendEndNo=None
rcvStartNo=None
rcvEndNo=None  

def makeLogfile(new_str):
    #print new_str
    wf.writelines(new_str+"\n")
    return

tgPstn=[]
scnt=0
rcnt=0
def makeData(i,a,b,c,d,e,send):
    global tgPstn
    global scnt
    global rcnt
    
    new_str =[]
    if send==1:
        #print "send"
        scnt=scnt+1
        tgPstn.append(b)
        #print tgPstn
    elif send==0 and tgPstn:
        
        #print "recieve"
        new_str.append(str(i))
        new_str.append(str(a))
        new_str.append(str(tgPstn[rcnt]))
        new_str.append(str(b))
        new_str.append(str(c))
        new_str.append(str(d))
        new_str.append(str(e)[4:9])
        new_str=' '.join(new_str)
        makeLogfile(new_str)
        rcnt=rcnt+1
        #print new_str
        #print "scnt:"+str(scnt)
        #print "rcnt:"+str(scnt)
       
        if scnt==rcnt:
            tgPstn=[]
            scnt=0
            rcnt=0
        
    
def bigEndianList(aLittleEndianHex):
    aBigEndianList = []
    for i in range(0, len(aLittleEndianHex), 2):
        aBigEndianList.insert(0, aLittleEndianHex[i:i+2])
    return ''.join(aBigEndianList)    
def s16(value):
    return -(value & 0x80000000) | (value & 0x7fffffff)
def extrac(x,y,send):
    #y=244
    #y=216
    #print y
    #print x
    i=1
    status=""
    apos=""
    avel=""
    mop=""
    dinputs=""
    
    temp=x[124:y]
    #print temp
    
    rest = len(x[124:y])%44
    #print rest
    mok = len(x[124:y])//44
    #print mok
    #elif rest==4:
    for i in range(1,3):
            #all
        temp2 = (temp[44*(i-1):(44*i)-14])
        #print temp2
            
            #status
        status=temp2[0:4]
        statusBigEndian=bigEndianList(status)
            #print statusBigEndian
            
            #apos
        apos=temp2[4:12]
        aposBigEndian = bigEndianList(apos)
            
        hex_str = aposBigEndian
        hex_int = int(hex_str, 16)
        aposdata=s16(hex_int) #hex to decimal
            #print aposdata            
            #avel 
        avel=temp2[12:20]
        avelBigEndian = bigEndianList(avel)
            
        hex_str = avelBigEndian
        hex_int = int(hex_str, 16)
        aveldata=s16(hex_int)#hex to decimal
            #print aveldata
            #mop
        mop=temp2[20:22]
            #print mop
            
            #dinputs
        dinputs=temp2[22:30]
        dinputsBig = bigEndianList(dinputs)
            #print dinputsBig
            
        makeData(i,statusBigEndian,aposdata,aveldata,mop,dinputsBig,send)
        i=i+1
        #i=1


def getDatagram(x,y,send):
    inStr =""
    outStr =""
    totStr =""
    if x is not None and y is not None and x >=1 and y >=0:   
        for i in range(x-1,y):
            inStr = lines[i].rstrip()
            #print inStr
            #print (inStr[inStr.find('EtherCAT DEBUG: ')+16:])
            
            outStr = inStr[63:len(inStr)]
            #outStr = inStr[inStr.find('EtherCAT DEBUG: ')+16:]
            #print outStr
            totStr=totStr+outStr
            totStr=totStr.replace(" ", "")
        extrac(totStr,len(totStr),send)
        #inStr=""
        #outStr =""
        #totStr =""

 
for line in lines:
    if line.find("Sending frame:") > 0:
        sendStartNo=ax+1      
    elif line.find("Received frame:")>0:
        sendEndNo=ax-1
        rcvStartNo=ax+1
        getDatagram(sendStartNo,sendEndNo,1)    
    elif line.find("ec_master_send_datagrams")>0:
        rcvEndNo=ax-1  
        getDatagram(rcvStartNo,rcvEndNo,0)    
    ax=ax+1
