rm -f /home/jscs1/justek_motionware/etherCAT_data/etherCAT.out
rm -f /var/log/etherCAT/etherCAT.log
sudo /etc/init.d/rsyslog stop
sudo /etc/init.d/rsyslog start
ethercat debug 2
echo "start done"
