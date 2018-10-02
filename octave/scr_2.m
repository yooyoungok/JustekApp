%사양정보를 이용하여 속도리플 구하기
spec_speedref= 10e5;     % 예) 기준속도 45 
spec_speedripple=0.001; % 예) 0.1% 속도리플 사양

load('data2.mat')
ref_hi=[spec_speedref*(1+ spec_speedripple) spec_speedref*(1+ spec_speedripple)];
ref_lo=[spec_speedref*(1- spec_speedripple) spec_speedref*(1- spec_speedripple)];
plot(t,vel_a,'g',[min(t) max(t)], ref_hi,'r--',[min(t) max(t)] ,ref_lo,'r--')
tmin=min(find(vel_a>spec_speedref*(1- spec_speedripple)));
tmax=max(find(vel_a>spec_speedref*(1- spec_speedripple)));
tmin=floor(tmin-(tmax-tmin)*0.1); 
if(tmin<1) tmin=1; end;
tmax=ceil(tmax+(tmax-tmin)*0.1);
if(tmax>size(vel_a,1)) tmax=size(vel_a,1); end;
axis([t(tmin) t(tmax) spec_speedref*(1- 2.5* spec_speedripple) spec_speedref*(1+ 2.5*spec_speedripple)])
xlabel('Time(s)')
ylabel('Velocity(m/s)')
title('Speed Ripple')
tx=text(0.5*t(tmin)+0.5*t(tmax),spec_speedref*(1+ 1.5*spec_speedripple),sprintf('Specification=+-%s%%',num2str(spec_speedripple*100)))
tx.HorizontalAlignment='center'