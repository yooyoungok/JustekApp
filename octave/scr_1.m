% 기본 그래프 그리기
load data
%ax=conv(ActualPosition,ones(1,100)/100);     %데이터가 지저분해서 필터링 했음
%ax=ax(1:5000);                                %데이터가 지저분해서 필터링 했음
%av=conv(ActualVelocity,ones(1,20)/20);                    %데이터가 지저분해서 필터링 했음
%av=av(1:5000);                                %데이터가 지저분해서 필터링 했음
%x=conv(TargetPosition,ones(1,100)/100);     %데이터가 지저분해서 필터링 했음
%x=x(1:5000);                                %데이터가 지저분해서 필터링 했음
%v=[0;diff(x)];                              %데이터가 지저분해서 필터링 했음
%v=conv(v,ones(1,20)/20);                    %데이터가 지저분해서 필터링 했음
%v=v(1:5000);                                %데이터가 지저분해서 필터링 했음
%a=[0;diff(v)];                              %데이터가 지저분해서 필터링 했음
%a=conv(a,ones(1,10)/10);                    %데이터가 지저분해서 필터링 했음
%a=a(1:5000);                                %데이터가 지저분해서 필터링 했음
[ah l1 l2]=plotyy(t,x-ax,t,a,'plot','plot')
axis(ah(2),[0 10 -0.2 0.2])
grid(ah(1))
l1.Color='r';
l2.Color='g';
xlabel('Time(s)')
ylabel(ah(1),'Error(cts)')
ylabel(ah(2),'Acceleration')

