%사양정보를 이용하여 세틀링타임 구하기
spec_window= 0.1;     % 예) 윈도우 0.1 

clf
load('data2.mat')
[ah l1 l2]=plotyy(t,pos_err,t,acc_c,'plot','plot')
% axis(ah(2),[0 10 -0.2 0.2])
grid(ah(1))
%l1.Color='r';
%l2.Color='b';
xlabel('Time(s)')
ylabel(ah(1),'Error(cts)')
ylabel(ah(2),'Acceleration')
title('Settling TIme')

tmin=max(find(vel_c>0));
tmax=max(find(abs(pos_err(tmin:size(pos_err,1)))> spec_window))+tmin;
t0=t(tmin);
ts=t(tmax);
tmin=floor(tmin-(tmax-tmin)*0.1); 
if(tmin<1) tmin=1; end;
tmax=ceil(tmax+(tmax-tmin)*0.9);
if(tmax>size(vel_a,1)) tmax=size(vel_a,1); end;

axis(ah(1), [t(tmin) t(tmax) -2*spec_window 2*spec_window])
axis(ah(2), [t(tmin) t(tmax) -4e7 4e7])
hold on
plot([t(tmin) t(tmax)],[spec_window spec_window],'--');
plot([t(tmin) t(tmax)],[-spec_window -spec_window],'--');
plot([t0 t0],[-2*spec_window 2*spec_window],'--');
plot([ts ts],[-2*spec_window 2*spec_window],'--');
tx=text(0.5*t(tmin)+0.5*t(tmax),spec_window*1.2,sprintf('    Settling time=+-%ss',num2str(ts-t0)))
tx.HorizontalAlignment='left'


% tx=text(0.5*t(tmin)+0.5*t(tmax),spec_speedref*(1+ 1.5*spec_speedripple),sprintf('Specification=%s%%',num2str(spec_speedripple*100)))
% tx.HorizontalAlignment='center'