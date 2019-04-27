#define _CRT_SECURE_NO_WARNINGS
#define row 15
#define col 15
#define Long 4
#include<stdio.h>
#include<stdlib.h>
#include<time.h>
void menu()
{
	printf("******************************\n");
	printf("********** 1.one play  *******\n");
	printf("********** 2.two play  *******\n");
	printf("********** 0.exit      *******\n");
	printf("******************************\n");
}
void initboard(char arr[row][col])//初始化数组
{
	int i = 0;
	int j = 0;
	for (i = 0; i < row; i++)
	{
		for (j = 0; j < col; j++)
		{
			arr[i][j] = ' ';
		}
	}
}
void printboard(char arr[row][col])//打印棋盘
{
	int i = 0;
	int j = 0;
	int a = 0;
	for (a = 1; a <= row; a++)
	{
		printf("%4d", a);
	}
	printf("\n");
	for (i = 0; i < row; i++)
	{
		for (j = 0; j < col; j++)
		{
			printf(" %c ", arr[i][j]);
			if (j <= col)
			{
				printf("|");
			}
		}
		printf("\n");
		for (j = 0; j < col; j++)
		{
			if (i <= row)
			{
				printf("---");
			}
			if (j <= col)
			{
				printf("|");
			}
		}
		printf("%d", i + 1);
		printf("\n");
	}
}
void palyer1move(char arr[row][col], int* a, int* b)//玩家1落子
{
	while (1)
	{
		printf("\n玩家走:\n请输入坐标");
		scanf("%d %d", a, b);
		if (*a > 0 && *a <= row && *b > 0 && *b <= col)
		{
			if (arr[*a - 1][*b - 1] == ' ')
			{
				arr[*a - 1][*b - 1] = '*';
				break;
			}
			else
			{
				printf("\n该位置已有棋子，请重新输入！\n");
			}
		}
		else
		{
			printf("\n非法坐标，请重新输入！\n");
		}
	}
}
void palyer2move(char arr[row][col], int* a, int* b)//玩家1落子
{
	while (1)
	{
		printf("\n玩家走:\n请输入坐标");
		scanf("%d %d", a, b);
		if (*a > 0 && *a <= row && *b > 0 && *b <= col)
		{
			if (arr[*a - 1][*b - 1] == ' ')
			{
				arr[*a - 1][*b - 1] = '$';
				break;
			}
			else
			{
				printf("\n该位置已有棋子，请重新输入！\n");
			}
		}
		else
		{
			printf("\n非法坐标，请重新输入！\n");
		}
	}
}
void computermove(char arr[row][col])//电脑落子
{
	int x = 0;
	int y = 0;
	x = (rand() % row);
	y = (rand() % col);
	while (arr[x][y] != ' ')
	{
		x = (rand() % row);
		y = (rand() % col);
	}
	printf("\n电脑走:\n");
	arr[x][y] = '#';
	//int x = 0;
	//int y = 0;
	//int t = 0;
	//printf("\n电脑走:\n");
	//arr[8][8] = '#';
	//for (x = 0; x < row; x++)
	//{
	//	for (y = 0; y < col; y++)
	//	{
	//		if (arr[x][y] != ' '&& arr[x][y] != '*')
	//		{
	//			t = Quanzhi(arr[x][y], x, y);//求出当t最大时的坐标并输出，如果有多个相同的最大值，则输出第一个
	//		}
	//	}
	//}

}
int judge(char arr[row][col], int a, int b)
{
	int count1 = 0;
	int count2 = 0;
	int count3 = 0;
	int count4 = 0;
	int count5 = 0;
	int count6 = 0;
	int count7 = 0;
	int count8 = 0;
	int n = 0;
	int i = 0;
	int j = 0;
	i = a - 1;
	j = b - 1;
	for (n = 1; n <= Long; n++)//方向向上
	{
		if (arr[i + n][j] == arr[i][j])
		{
			count1++;
		}
		else
		{
			count1 = 0;
			break;
		}
	}
	for (n = 1; n <= Long; n++)//方向向下
	{
		if (arr[i - n][j] == arr[i][j])
		{
			count2++;
		}
		else
		{
			count2 = 0;
			break;
		}
	}
	for (n = 1; n <= Long; n++)//方向向左
	{
		if (arr[i][j - n] == arr[i][j])
		{
			count3++;
		}
		else
		{
			count3 = 0;
			break;
		}
	}
	for (n = 1; n <= Long; n++)//方向向右
	{
		if (arr[i][j + n] == arr[i][j])
		{
			count4++;
		}
		else
		{
			count4 = 0;
			break;
		}
	}
	for (n = 1; n <= Long; n++)//方向向左下
	{
		if (arr[i - n][j - n] == arr[i][j])
		{
			count5++;
		}
		else
		{
			count5 = 0;
			break;
		}
	}
	for (n = 1; n <= Long; n++)//方向向左上
	{
		if (arr[i - n][j + n] == arr[i][j])
		{
			count6++;
		}
		else
		{
			count6 = 0;
			break;
		}
	}
	for (n = 1; n <= Long; n++)//方向向右下
	{
		if (arr[i + n][j - n] == arr[i][j])
		{
			count7++;
		}
		else
		{
			count7 = 0;
			break;
		}
	}
	for (n = 1; n <= Long; n++)//方向向右上
	{
		if (arr[i + n][j + n] == arr[i][j])
		{
			count8++;
		}
		else
		{
			count8 = 0;
			break;
		}
	}
	if ((count1 + count2 == Long) || (count3 + count4 == Long) ||
		(count5 + count8 == Long) || (count6 + count7 == Long))
	{
		return 0;
	}
	else
	{
		return 1;
	}
}
void game1()
{
	char arr[row][col];
	int flag = 0;
	initboard(arr);
	printboard(arr);
	while (1)
	{
		int a;
		int b;
		palyer1move(arr, &a, &b);
		printboard(arr);
		flag = judge(arr, a, b);
		if (flag == 0)
		{
			break;
		}
		computermove(arr);
		printboard(arr);
		flag = judge(arr, a, b);
	}
}
void game2()
{
	char arr[row][col];
	int flag = 0;
	initboard(arr);
	printboard(arr);
	while (1)
	{
		int a;
		int b;
		int c;
		int d;
		palyer1move(arr, &a, &b);
		printboard(arr);
		flag = judge(arr, a, b);
		if (flag == 0)
		{
			break;
		}
		palyer2move(arr, &c, &d);
		printboard(arr);
		flag = judge(arr, c, d);
		if (flag == 0)
		{
			break;
		}
	}
}
void While(int input)
{
	while (input)
	{
		if (input == 1)
		{
			game1();
			printf("游戏结束,欢迎下次再玩！\n");
			break;
		}
		else if (input == 0)
		{
			break;
		}
		else if (input == 2)
		{
			game2();
			printf("游戏结束,欢迎下次再玩！\n");
			break;
		}
		else
		{
			printf("选择错误，请重新输入！\n");
			scanf("%d", &input);
			While(input);
		}
	}
}
int main()
{
	int input = 0;
	srand((unsigned)time(0));
	menu();
	scanf("%d", &input);
	While(input);
	system("pause");
	return 0;
}