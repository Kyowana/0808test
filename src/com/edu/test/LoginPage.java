package com.edu.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.edu.entity.Order;
import com.edu.entity.TrainInfo;
import com.edu.entity.UserInfo;


public class LoginPage {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String pattern = "[a-z0-9]*";
		List<UserInfo> listU = new ArrayList<>();
		List<TrainInfo> listT = new ArrayList<>();
		listT.add(new TrainInfo("G101", 100, 50));
		listT.add(new TrainInfo("G102", 200, 35));
		listT.add(new TrainInfo("G103", 300, 30));
		listT.add(new TrainInfo("G104", 250, 40));
		listT.add(new TrainInfo("G105", 150, 45));
		List<Order> allOrder = new ArrayList<>();
		UserInfo nowUser = new UserInfo();
		
		while(true) {
			System.out.println("==========歡迎使用購票系統==========");
			if (nowUser.getName() != null) {
				System.out.println("3. 購買車票");
				System.out.println("4. 我的訂單");
				System.out.println("5. 退出");				
			} else {
				System.out.println("1. 註冊");
				System.out.println("2. 登入");
			}
			String op1 = scan.next();
			if (op1 == null) {
				System.out.println("輸入錯誤，請重新選擇功能");
				continue;
			}
			if (op1.equals("1")) {
				if (nowUser.getName() != null) {
					System.out.println("已登入用戶無法使用此功能，跳轉回首頁功能選單");
					continue;
				}
				if (listU.size() < 100) {
					register(scan, pattern, listU);					
				} else {
					System.out.println("目前用戶數已滿額，不開放新用戶註冊");
				}
			} else if (op1.equals("2")){
				if (nowUser.getName() != null) {
					System.out.println("已登入用戶無法使用此功能，跳轉回首頁功能選單");
					continue;
				}
//				UserInfo nowUser = new UserInfo();
				boolean isCorrect = false;
				boolean isCorrect1 = false;
				for (int i = 0; i < 3; i++) {
					System.out.println("請登入系統");
					System.out.print("請輸入帳號: ");
					String name = scan.next();					
					nowUser.setName(name);
					System.out.print("請輸入密碼: ");
					String pwd = scan.next();
					nowUser.setPwd(pwd);
					if (name == null || pwd == null) {
						System.out.println("帳號或密碼請勿留空");
						continue;
					}
					for (UserInfo userInfo : listU) {
						if (userInfo.getName().equals(name) && userInfo.getPwd().equals(pwd)) {
							isCorrect = true;
							System.out.println("登入成功");
				            break;
				        }
					}
					if (isCorrect)
						break;
					System.out.println("帳號或密碼錯誤，剩餘" + (2-i) + "次機會");
					if (i == 2 && !isCorrect) {
						nowUser.setName(null);
						System.out.println("連續輸入錯誤達3次");
						while(true) {
							System.out.println("=====找回密碼=====");
							System.out.print("請輸入帳號: ");
							String name1 = scan.next();
							System.out.print("請輸入郵箱: ");
							String email1 = scan.next();
//							boolean isCorrect1 = false;
							if (name1 == null || email1 == null) {
								System.out.println("輸入錯誤");
								if (askContinue(scan)) {
									continue;									
								} else {
									break;
								}
							}
							for (UserInfo userInfo : listU) {
								if (userInfo.getName().equals(name1) && userInfo.getEmail().equals(email1)) {
									while(true) {
										System.out.print("帳號及郵箱輸入正確，請輸入新密碼: ");
										String pwd1 = scan.next();
										if(pwd1 == null) {
											System.out.println("密碼請勿留空");
										} else {
											isCorrect1 = true;
											userInfo.setPwd(pwd1);
											break;						
										}										
									}
						            break;
						        }
							}
							if (!isCorrect1) {
								System.out.println("輸入錯誤");
								if (askContinue(scan)) {
									continue;									
								} else {
									break;
								}
							} else {
								break;
							}
						}
					}
				}
				if (isCorrect) {
					System.out.println("登入成功，跳轉至首頁功能選單");
				}
				if (isCorrect1) {
					System.out.println("密碼重設成功，跳轉至首頁功能選單，請重新登入");
				}
			} else if (op1.equals("3")){
				if (nowUser.getName() == null) {
					System.out.println("未登入無法使用此功能，跳轉回首頁功能選單");
					continue;
				}
				System.out.println("車次\t價格\t剩餘數量");
				for (TrainInfo item : listT) {
					item.printInfo(item);
				}
				TrainInfo train = new TrainInfo();
				while(true) {
					System.out.print("請輸入欲購買車次: ");
					String trip = scan.next();
					if (trip == null || !listT.stream().filter(o -> o.getTrip().equals(trip)).findFirst().isPresent()) {
						System.out.println("查無此車次! 請重新輸入");
						continue;
					}
					boolean canBuy = false;
					for (TrainInfo item : listT) {
						if (item.getTrip().equals(trip)) {
							if (item.getTickets() == 0) {
								System.out.println("本車次已無可購買車票");
							} else {
								canBuy = true;
							}
							break;
						}
					}
					if(canBuy) {
						train.setTrip(trip);
						break;
					}
				}
				
				while(true) {
					System.out.print("請輸入欲購買數量: ");
					int num = scan.nextInt();  //
					if (num == 0) {
						System.out.println("購買數量勿為0，請重新輸入");
						continue;
					}
					boolean isBought = false;
					for (TrainInfo item : listT) {
						if (item.getTrip().equals(train.getTrip())) {
							if (item.getTickets() < num) {
								System.out.println("餘票不足，請重新輸入");
							} else {
								item.setTickets(item.getTickets() - num);
								System.out.println("購買成功! 跳轉回首頁功能選單");
								train.setTickets(num);
								train.setPrice(item.getPrice() * num);
								isBought = true;
							}							
							break;
						}						
					}
					if(isBought) {
						allOrder.add(new Order(nowUser.getName(), train));
						break;
					}
				}
				
			} else if (op1.equals("4")){
				if (nowUser.getName() == null) {
					System.out.println("未登入無法使用此功能，跳轉回首頁功能選單");
					continue;
				}
				System.out.println("我的訂單:");
				String name = nowUser.getName();
				if (!allOrder.stream().filter(o -> o.getUserName().equals(name)).findFirst().isPresent()) {
					System.out.println("無購買紀錄");					
				} else {
					for (Order item : allOrder) {
						if (name.equals(item.getUserName())) {
							item.printInfo(name);
						}
					}					
				}
				while(true) {
					System.out.print("輸入N返回主選單: ");
					String yn = scan.next();
					if (yn.equalsIgnoreCase("N")) {
						System.out.println("回到系統首頁");
						break;  //
					} else {
						System.out.println("輸入錯誤，請重新輸入");
					}							
				}
				
			}else if (op1.equals("5")){
				if (nowUser.getName() == null) {
					System.out.println("未登入無法使用此功能，跳轉回首頁功能選單");
					continue;
				}
				nowUser.setName(null);
				System.out.println("已登出，回到首頁");
				
			} else {
				System.out.println("輸入錯誤，請重新選擇功能");
			}
		}

	}
	
	
	private static boolean askContinue(Scanner scan) {
		while(true) {
			System.out.print("是否繼續[y/n]: ");
			String yn = scan.next();
			if (yn.equalsIgnoreCase("Y")) {
				return true;
			} else if (yn.equalsIgnoreCase("N")) {
				System.out.println("回到系統首頁");
				return false;  //
			} else {
				System.out.println("輸入錯誤，請重新輸入");
			}							
		}
	}
	
	private static void register(Scanner scan, String pattern, List<UserInfo> listU) {
		UserInfo user = new UserInfo();
		while(true) {
			System.out.print("請註冊帳號: ");
			String name = scan.next();
			if (name == null) {
				System.out.println("帳號請勿留空");
				continue;
			}
			if(!name.matches(pattern) || name.length() < 6) {
				System.out.println("帳號長度須大於6位，並且只可使用小寫英文字母及數字");
			} else {
				for (UserInfo userInfo : listU) {
					if (userInfo.getName().equals(name)) {
						System.out.println("已有同名用戶，請重新輸入");
			            break;
			        }
				}
				user.setName(name);
				break;
			}
		}
		while(true) {
			System.out.print("請設定密碼: ");
			String pwd = scan.next();
			if(pwd == null) {
				System.out.println("密碼請勿留空");
			} else {
				user.setPwd(pwd);
				break;						
			}
		}
		while(true) {
			System.out.print("請填寫郵箱: ");
			String email = scan.next();
			if(email == null) {
				System.out.println("郵箱請勿留空");
			} else {
				user.setEmail(email);
				System.out.println("註冊成功");
				break;						
			}
		}
		listU.add(user);
	}

}
