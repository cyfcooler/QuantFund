source utils.sh
rm -rf selected_number.txt
for i in `cat 'selected.txt' | awk '{print $1}'`
do
	result=`is_number $i`
	if [ $result -eq 0 ];then
		echo "Got "$i
		echo $i >> selected_number.txt
	fi
done

cat selected_number.txt | sort > tmp.txt
rm -rf selected_number.txt
mv tmp.txt selected_number.txt
