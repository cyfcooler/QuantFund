source ./utils.sh

./process_selected.sh
for i in `cat ./selected_number.txt`
do
echo "Delete "$i
operation d $i
done
