# Compute 2.0^x

var x: int;

var y: float;

var s : string;


x = 10;

y = 1.0;

s = "yanis";

while x do

	if (x) then 
		y = 9;
	endif

   y = y * 2.0;

   x = x - 1;

done

while 3 do

done

if x then

  x = 0;

else

  y = 0;

endif

# we disallow -012, -00, 012, -0, 00

y = -0;

y = 23.45;
y = .45 ;
y = 0.45 ;
y = 23. ;

print y;

read x;

s = s - s; 

print s;

x = 3- -((3 + (3 * (-3))))-3;
x = --3;
x = -5 * -3;
x = -2 - -(-5 * -2);