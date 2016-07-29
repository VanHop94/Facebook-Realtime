/*
Navicat MySQL Data Transfer

Source Server         : VanHop
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : realtimepost

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-12-04 15:58:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` bigint(20) NOT NULL,
  `like_variation` bigint(20) DEFAULT NULL,
  `share_variation` bigint(20) DEFAULT NULL,
  `comment_variation` bigint(20) DEFAULT NULL,
  `action_observation` text,
  `update_count` int(11) DEFAULT NULL,
  `time_update` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `post` (`post_id`),
  CONSTRAINT `action_on_post` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3295 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of action
-- ----------------------------
INSERT INTO `action` VALUES ('1204', '21', '3', '0', '0', '{\"list\":[{\"comments\":0,\"like\":8,\"share\":0,\"time\":1449209157570},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449209161229},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449209165793},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449209169634},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449209173018},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449209176516},{\"comments\":0,\"like\":5,\"share\":0,\"time\":1449209180034},{\"comments\":0,\"like\":6,\"share\":0,\"time\":1449209183619},{\"comments\":0,\"like\":4,\"share\":0,\"time\":1449209187301},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449209190895},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449209194486},{\"comments\":0,\"like\":8,\"share\":0,\"time\":1449209198182},{\"comments\":0,\"like\":4,\"share\":0,\"time\":1449209202416},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449209206043},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449209209771},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449209213590},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449209217330},{\"comments\":0,\"like\":4,\"share\":0,\"time\":1449209221402},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449209226617},{\"comments\":0,\"like\":4,\"share\":0,\"time\":1449209230552},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449209234138},{\"comments\":0,\"like\":6,\"share\":0,\"time\":1449209238052},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449209241635},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449209245217},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449209249044},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449209252659},{\"comments\":0,\"like\":4,\"share\":0,\"time\":1449209256110},{\"comments\":0,\"like\":5,\"share\":0,\"time\":1449209259595},{\"comments\":1,\"like\":1,\"share\":0,\"time\":1449209262980},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449209266497}]}', '1032', '1449205674349');
INSERT INTO `action` VALUES ('2237', '22', '0', '0', '0', '{\"list\":[{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449212240812},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212242909},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212244780},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449212246842},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212248606},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212251407},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212253195},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212255089},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212257010},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212258821},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212261116},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212262952},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212264764},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212266572},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212268371},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212270450},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212272598},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212275139},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212277190},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212279245},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212281434},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212283599},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212285808},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449212288172},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449212291440},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212293483},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212295486},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212297398},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212299468},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449212301426}]}', '747', '1449210748269');
INSERT INTO `action` VALUES ('2985', '23', '1', '0', '0', '{\"list\":[{\"comments\":1,\"like\":0,\"share\":0,\"time\":1449216005783},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449216008195},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449216010819},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449216013325},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449216015969},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449216018382},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449216021384},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449216024218},{\"comments\":0,\"like\":4,\"share\":0,\"time\":1449216026831},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449216029527},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449216032478},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449216035004},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449216037726},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449216040667},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449216044136},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449216046603},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449216049198},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449216051851},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449216054388},{\"comments\":0,\"like\":5,\"share\":0,\"time\":1449216057448},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449216060995},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449216064580},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449216067286},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449216069985},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449216072688},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449216075947},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449216078678},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449216081967},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449216085381},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449216088785}]}', '218', '1449215270488');
INSERT INTO `action` VALUES ('3204', '24', '2', '0', '0', '{\"list\":[{\"comments\":1,\"like\":2,\"share\":0,\"time\":1449219396972},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449219399719},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449219402878},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449219405781},{\"comments\":0,\"like\":4,\"share\":0,\"time\":1449219409350},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449219412908},{\"comments\":1,\"like\":5,\"share\":0,\"time\":1449219416634},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449219419850},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449219423033},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449219425849},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449219429697},{\"comments\":0,\"like\":0,\"share\":0,\"time\":1449219432677},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449219435839},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449219439670},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449219443142},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449219446503},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449219449515},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449219452481},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449219455659},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449219460992},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449219464305},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449219467524},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449219470931},{\"comments\":0,\"like\":4,\"share\":0,\"time\":1449219474873},{\"comments\":0,\"like\":3,\"share\":0,\"time\":1449219478125},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449219481213},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449219484380},{\"comments\":0,\"like\":1,\"share\":0,\"time\":1449219487321},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449219490440},{\"comments\":0,\"like\":2,\"share\":0,\"time\":1449219494134}]}', '90', '1449219205650');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` varchar(100) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  `user_id` varchar(100) NOT NULL,
  `datetime` text,
  `content` longtext,
  `content_2` longtext,
  `status` int(11) DEFAULT NULL,
  `like_count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `comment_id` (`comment_id`,`post_id`),
  KEY `post` (`post_id`),
  CONSTRAINT `post` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=175810 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('152587', '10156843031560206_10156843249175206', '21', '173251313028871', '1449205546000', 'Thế giới cứ 100 người . Có 50 người gian dối . 40 người vì tiền . 9 người lừa gạt . . Chỉ có 1 người tốt bụng , dễ thương là đang đọc cái stt này và sẽ like nó...((>^,^<)) trưa mát nha mọi ng', 'the gioi cu 100 nguoi . co 50 nguoi gian doi . 40 nguoi vi tien . 9 nguoi lua gat . . chi co 1 nguoi tot bung , de thuong la dang doc cai stt nay va se like no...((>^,^<)) trua mat nha moi ng', '0', '18');
INSERT INTO `comment` VALUES ('152588', '10156843031560206_10156843249635206', '21', '219663031698375', '1449205556000', 'Sáng đi ngân hàng rút tiền, thấy đông quá không có chỗ để xe. Đành dừng xe bên ngoài, còn bảo thằng e: \"Nếu có CA đến bắt xe thì nhớ báo cho a 1 tiếng\". Quả nhiên vừa vào được vài phút thì CSGT đến. Thằng kia xông vào trong ngân hàng gào ầm lên : - \" Đại ca, cảnh sát đến, chạy mau!!\" Mẹ nó chứ, cả cái đại sảnh to mấy chục con người lập tức yên lặng không 1 tiếng động. Sau đó cả đám đông ùn ùn chen nhau chạy ra khỏi ngân hàng như lũ quét. Mình đang ngơ ngác ko hiểu chuyện gì thì bất ngờ bị 5-6 nhân viên bảo vệ ấn đầu xuống đất =))', 'sang di ngan hang rut tien, thay dong qua khong co cho de xe. đanh dung xe ben ngoai, con bao thang e: \"neu co ca den bat xe thi nho bao cho a 1 tieng\". qua nhien vua vao duoc vai phut thi csgt den. thang kia xong vao trong ngan hang gao am len : - \" đai ca, canh sat den, chay mau!!\" me no chu, ca cai dai sanh to may chuc con nguoi lap tuc yen lang khong 1 tieng dong. sau do ca dam dong un un chen nhau chay ra khoi ngan hang nhu lu quet. minh dang ngo ngac ko hieu chuyen gi thi bat ngo bi 5-6 nhan vien bao ve an dau xuong dat =))', '0', '11');
INSERT INTO `comment` VALUES ('152589', '10156843031560206_10156843249785206', '21', '1519128921735179', '1449205559000', 'Tóm Tắt đây lần cuồi không tóm tắt nữa Top lần này nữa thôi :)) . . . . . . ...Xem Thêm', 'tom tat day lan cuoi khong tom tat nua top lan nay nua thoi :)) . . . . . . ...xem them', '0', '10');
INSERT INTO `comment` VALUES ('152590', '10156843031560206_10156843250605206', '21', '124163794618590', '1449205576000', 'E tốp', 'e top', '0', '7');
INSERT INTO `comment` VALUES ('152591', '10156843031560206_10156843251695206', '21', '1708478669387723', '1449205600000', 'áo dài việt Nam.. có giải chắc :D', 'ao dai viet nam.. co giai chac :d', '0', '27');
INSERT INTO `comment` VALUES ('152592', '10156843031560206_10156843251925206', '21', '692003154270273', '1449205606000', 'Xuất sắc <3', 'xuat sac <3', '0', '7');
INSERT INTO `comment` VALUES ('152677', '10156843031560206_10156843256955206', '21', '1672317622981461', '1449205709000', 'Sao chang thay ai dua tin Huynh Van Nen nhi', 'sao chang thay ai dua tin huynh van nen nhi', '0', '4');
INSERT INTO `comment` VALUES ('152692', '10156843031560206_10156843257275206', '21', '1650018875268647', '1449205715000', 'https:\\\\M.FaCeBoOk.COM/????????????????hc_location=ufi#/?qwirypueuwpiowhiiqiiq%uyrpttwep1popqttoo@uowuyyqtiortuqputeirqwyopqepweoirup&hc_location=ufi#/note.php?note_id=412069015655127#/2eiptiiw%@Bsl', 'https:\\\\m.facebook.com/????????????????hc_location=ufi#/?qwirypueuwpiowhiiqiiq%uyrpttwep1popqttoo@uowuyyqtiortuqputeirqwyopqepweoirup&hc_location=ufi#/note.php?note_id=412069015655127#/2eiptiiw%@bsl', '0', '3');
INSERT INTO `comment` VALUES ('152725', '10156843031560206_10156843257805206', '21', '539772389532339', '1449205727000', 'Cách nhận diện trẻ trâu trên facebook Biểu tượng cảm xúc smile 1. Tên thường có : - Gắn thêm \'x, \'s, \'sss... - Đôi khi là cả \'h hoặc kí tự đặc biệt như trái tim, cánh bướm... - Hoặc Đời Là Gì, Ừk Tkì Đếck Cần, Ck của..., Vk của ...v...v... - Hoặc có thêm chữ Sky... 2. Thường làm việc tại: - Đại Học Bôn Ba - Học viện Hàng Không ( Ăn hàng - Ở không ) - Ở đâu còn lâu mới nói - Em yêu anh, yêu cả con chó nhà anh 3. Avatar : - Mặt thì non chẹt, nhe răng chắc toàn răng sữa, tóc tai như HKT. - Đôi khi đeo khẩu trang y tế, hay chụp hình xăm... - Hoặc chụp dream chiến, bốc đầu bốc đuôi các kiểu con đà điểu. - Hoặc chụp ảnh trước gương với con Iphone đi mượn, mồm chu như khỉ hoặc mắt nhìn trừng trừng vào ống kính 4. Ảnh bìa : - Nào thì anh em xương máu, thằng nào động đến anh em bố thì bố mày ko để yên - Hoặc là khóc xong chụp ảnh, hận đời, hận ng yêu xong lấy dao lam cắt rạch tay. - Rồi thì Genji trong bá vương học đường - Rồi thì tiền học của cha mẹ cả năm mới được cái ảnh quẩy trên bar, hút shisha chân tay múa múa. Bla bla...!!!', 'cach nhan dien tre trau tren facebook bieu tuong cam xuc smile 1. ten thuong co : - gan them \'x, \'s, \'sss... - đoi khi la ca \'h hoac ki tu dac biet nhu trai tim, canh buom... - hoac đoi la gi, uk tki đeck can, ck cua..., vk cua ...v...v... - hoac co them chu sky... 2. thuong lam viec tai: - đai hoc bon ba - hoc vien hang khong ( an hang - o khong ) - o dau con lau moi noi - em yeu anh, yeu ca con cho nha anh 3. avatar : - mat thi non chet, nhe rang chac toan rang sua, toc tai nhu hkt. - đoi khi deo khau trang y te, hay chup hinh xam... - hoac chup dream chien, boc dau boc duoi cac kieu con da dieu. - hoac chup anh truoc guong voi con iphone di muon, mom chu nhu khi hoac mat nhin trung trung vao ong kinh 4. anh bia : - nao thi anh em xuong mau, thang nao dong den anh em bo thi bo may ko de yen - hoac la khoc xong chup anh, han doi, han ng yeu xong lay dao lam cat rach tay. - roi thi genji trong ba vuong hoc duong - roi thi tien hoc cua cha me ca nam moi duoc cai anh quay tren bar, hut shisha chan tay mua mua. bla bla...!!!', '0', '6');
INSERT INTO `comment` VALUES ('152744', '10156843031560206_10156843258075206', '21', '1516669881984336', '1449205732000', 'đại tỉ chứ sao đại ca? vl cú có gai!nhá', 'dai ti chu sao dai ca? vl cu co gai!nha', '0', '2');
INSERT INTO `comment` VALUES ('153345', '10156843031560206_10156843273045206', '21', '114314732271495', '1449205904000', 'mình đang bán tiền giả (ba loại tiền 100k.200k.500k) loại tờ 100 5 triệu tiền giả = 1 triệu 500 thật loại tờ 200 5 triệu tiền giả = 1 triệu 400 thật loại tờ 500 5 triệu tiền giả = 1 triệu 300 thật anh em nào cần nhắn tin', 'minh dang ban tien gia (ba loai tien 100k.200k.500k) loai to 100 5 trieu tien gia = 1 trieu 500 that loai to 200 5 trieu tien gia = 1 trieu 400 that loai to 500 5 trieu tien gia = 1 trieu 300 that anh em nao can nhan tin', '0', '1');
INSERT INTO `comment` VALUES ('154193', '10156843031560206_10156843287470206', '21', '427401837451248', '1449206145000', 'Đặc tin tưởng vào c hết đấy', 'đac tin tuong vao c het day', '0', '1');
INSERT INTO `comment` VALUES ('154806', '10156843031560206_10156843296595206', '21', '428616550665761', '1449206310000', 'Cị ấy thật tuyệt', 'ci ay that tuyet', '0', '3');
INSERT INTO `comment` VALUES ('154937', '10156843031560206_10156843298255206', '21', '631591446980715', '1449206347000', 'Niềm tự hào của Việt Nam', 'niem tu hao cua viet nam', '0', '4');
INSERT INTO `comment` VALUES ('155316', '10156843031560206_10156843302075206', '21', '1657379167855260', '1449206428000', 'Đẹp ? ', 'đep ? ', '0', '3');
INSERT INTO `comment` VALUES ('155572', '10156843031560206_10156843305095206', '21', '490376231136804', '1449206483000', 'Niem tu hao cua VN! Vhiec ao dai va mu niem tuyet dep va an tuong cua truyen thog VN! Hy vog se co giai dac biet!!!', 'niem tu hao cua vn! vhiec ao dai va mu niem tuyet dep va an tuong cua truyen thog vn! hy vog se co giai dac biet!!!', '0', '4');
INSERT INTO `comment` VALUES ('156053', '10156843031560206_10156843309745206', '21', '1967037776853688', '1449206579000', 'Đẹp qá <3', 'đep qa <3', '0', '0');
INSERT INTO `comment` VALUES ('158519', '10156843031560206_10156843334650206', '21', '906061816114662', '1449207065000', 'That hanh dien cho To quoc neu ban dat dc danh hieu lan nay nhi...??!!!', 'that hanh dien cho to quoc neu ban dat dc danh hieu lan nay nhi...??!!!', '0', '0');
INSERT INTO `comment` VALUES ('160878', '10156843031560206_10156843355030206', '21', '1687813048125808', '1449207517000', 'C này nhìn mặt rất Tây càng nhìn càng thấy phê thế nào ý dù mình là con gái ? ? ? ', 'c nay nhin mat rat tay cang nhin cang thay phe the nao y du minh la con gai ? ? ? ', '0', '3');
INSERT INTO `comment` VALUES ('163421', '10156843031560206_10156843380595206', '21', '763872637073946', '1449208000000', 'Nhu the nay moi la hoa hau chu?ai nhu KD', 'nhu the nay moi la hoa hau chu?ai nhu kd', '0', '3');
INSERT INTO `comment` VALUES ('165113', '10156843031560206_10156843397465206', '21', '1748696078686599', '1449208328000', 'Đẹp mê hồn', 'đep me hon', '0', '0');
INSERT INTO `comment` VALUES ('165654', '10156843031560206_10156843408055206', '21', '448979411954403', '1449208462000', 'Mong mỏi quá', 'mong moi qua', '0', '0');
INSERT INTO `comment` VALUES ('167398', '10156843031560206_10156843430940206', '21', '1688849364706174', '1449208782000', 'Hồi đầu thấy PH chả đẹp tí nào,miệng quá rộng,xong gần đây ms thấy chị ý đẹp lạ,đẹp như tây,lại thích luôn nụ cười,càng nhìn càng thấy thích ấy', 'hoi dau thay ph cha dep ti nao,mieng qua rong,xong gan day ms thay chi y dep la,dep nhu tay,lai thich luon nu cuoi,cang nhin cang thay thich ay', '0', '1');
INSERT INTO `comment` VALUES ('170105', '10156843031560206_10156843456975206', '21', '737080226423839', '1449209260000', 'C ý đẹp kiểu tây tây , kiểu hoa hậu thế giới ý', 'c y dep kieu tay tay , kieu hoa hau the gioi y', '0', '0');
INSERT INTO `comment` VALUES ('170129', '10156843043135206_10156843505685206', '22', '142351609463162', '1449210331000', 'Đẹp ..... vl :)))', 'đep ..... vl :)))', '1', '5');
INSERT INTO `comment` VALUES ('170138', '10156843043135206_10156843522640206', '22', '173251313028871', '1449210763000', 'Hai vợ chồng nhà nọ cãi nhau - Anh chồng nói: Tốt nhất chúng ta chia tay đi - Người vợ tiếp lời: Nếu điều đó làm anh vui.. Anh chồng ra điều kiện: Chúng ta mỗi người bước đi 100 bước về 2 hướng khác nhau, nếu hết 100 bước mà cả hai quay đầu lại thì coi như không có chuyện gì, còn không thì về sau này nếu có gặp lại nhau chúng ta vẫn coi nhau là bạn bè nhé. Anh chồng kìm lòng bước qua 99 bước, đến bước cuối cùng thì quay đầu lại,...sững sờ khi thấy người vợ không đi về hướng ngược lại mà đi theo ngay sau lưng mình. - Người vợ điềm tĩnh nói: Chỉ cần anh quay lại, em sẽ luôn ở phía sau anh. Anh chồng nghẹn ngào nấc không thành tiếng, ôm choàng vợ vào lòng rưng rưng, còn người vợ từ từ lẳng viên gạch giấu trong người xuống bụi cây, nghĩ thầm trong bụng: Chỉ cần mày bước thêm 1 bước nữa, viên gạch của bà sẽ cho nát cmn đầu mày luôn', 'hai vo chong nha no cai nhau - anh chong noi: tot nhat chung ta chia tay di - nguoi vo tiep loi: neu dieu do lam anh vui.. anh chong ra dieu kien: chung ta moi nguoi buoc di 100 buoc ve 2 huong khac nhau, neu het 100 buoc ma ca hai quay dau lai thi coi nhu khong co chuyen gi, con khong thi ve sau nay neu co gap lai nhau chung ta van coi nhau la ban be nhe. anh chong kim long buoc qua 99 buoc, den buoc cuoi cung thi quay dau lai,...sung so khi thay nguoi vo khong di ve huong nguoc lai ma di theo ngay sau lung minh. - nguoi vo diem tinh noi: chi can anh quay lai, em se luon o phia sau anh. anh chong nghen ngao nac khong thanh tieng, om choang vo vao long rung rung, con nguoi vo tu tu lang vien gach giau trong nguoi xuong bui cay, nghi tham trong bung: chi can may buoc them 1 buoc nua, vien gach cua ba se cho nat cmn dau may luon', '0', '1');
INSERT INTO `comment` VALUES ('170199', '10156843043135206_10156843525225206', '22', '1692966384251264', '1449210829000', 'chuyên thu mua tất cả các loại xe máy đã qua sử dụng đảm bảo giá tốt nhất TP HCM 0909490221 A Tan . mua bán tại nhà quý khách ,thủ tục sang tên tự lo ,thanh toán bằng tiền mặt một lần duy nhất .Khi cần bán xe quý khách gọi điện thoại hoặc nhấn tin chúng tôi sẽ gọi lại ngay . Đảm bảo mua giá cao UY TÍN -NHIỆT TÌNH -VUI VẺ ,cảm ơn vì sự hợp tác và quan tâm của quy vị . XE MÁY TÂN TIẾN 0909490221 Website page tham khảo https://www.facebook.com/huyen.churi', 'chuyen thu mua tat ca cac loai xe may da qua su dung dam bao gia tot nhat tp hcm 0909490221 a tan . mua ban tai nha quy khach ,thu tuc sang ten tu lo ,thanh toan bang tien mat mot lan duy nhat .khi can ban xe quy khach goi dien thoai hoac nhan tin chung toi se goi lai ngay . đam bao mua gia cao uy tin -nhiet tinh -vui ve ,cam on vi su hop tac va quan tam cua quy vi . xe may tan tien 0909490221 website page tham khao https://www.facebook.com/huyen.churi', '0', '0');
INSERT INTO `comment` VALUES ('170371', '10156843043135206_10156843533165206', '22', '131083330592066', '1449211003000', 'Độ đệ ta đâu hết rồi.', 'đo de ta dau het roi.', '0', '0');
INSERT INTO `comment` VALUES ('172262', '10156843696510206_10156843741250206', '23', '1679313698972925', '1449215139000', 'điều đó có khiến cmt này tóp dc ko :3', 'dieu do co khien cmt nay top dc ko :3', '1', '15');
INSERT INTO `comment` VALUES ('172263', '10156843696510206_10156843742505206', '23', '158956491125821', '1449215177000', 'méo tin ✔', 'meo tin ✔', '1', '12');
INSERT INTO `comment` VALUES ('172264', '10156843696510206_10156843742560206', '23', '423060714557888', '1449215181000', 'cc tòab đăg những cái k hay', 'cc toab dag nhung cai k hay', '0', '11');
INSERT INTO `comment` VALUES ('172265', '10156843696510206_10156843742685206', '23', '1516669881984336', '1449215187000', 'vậy cũng \"động trời\" :3', 'vay cung \"dong troi\" :3', '0', '11');
INSERT INTO `comment` VALUES ('172266', '10156843696510206_10156843742960206', '23', '149571855404307', '1449215194000', 'cần bán 1 con 1202 lỗi nhẹ . rơi xuống nước,chập main , mất rung , pin đểu , vỏ gãy , sim lúc nhận lúc không , sóng lúc nhiều lúc ít , sài 5 phút sập nguồn , bật lại lên , sài 20 phút nóng máy hết pin , đèn màn hình mờ , đèn bàn phím chết , liệt phím gọi nghe , 2 , 4 , 6.thỉnh thoảng hư cả bàn phím . Đúng giá 400k, không nói điêu , không bảo hành , không có sạc vì máy không sạc được , toàn dùng sạc không dây . đã có người trả 380k rồi chưa bán', 'can ban 1 con 1202 loi nhe . roi xuong nuoc,chap main , mat rung , pin deu , vo gay , sim luc nhan luc khong , song luc nhieu luc it , sai 5 phut sap nguon , bat lai len , sai 20 phut nong may het pin , den man hinh mo , den ban phim chet , liet phim goi nghe , 2 , 4 , 6.thinh thoang hu ca ban phim . đung gia 400k, khong noi dieu , khong bao hanh , khong co sac vi may khong sac duoc , toan dung sac khong day . da co nguoi tra 380k roi chua ban', '0', '12');
INSERT INTO `comment` VALUES ('172267', '10156843696510206_10156843743140206', '23', '1679313698972925', '1449215202000', 'Vũ Hoàng', 'vu hoang', '0', '0');
INSERT INTO `comment` VALUES ('172268', '10156843696510206_10156843743330206', '23', '1679313698972925', '1449215206000', 'Phúc Cao', 'phuc cao', '0', '0');
INSERT INTO `comment` VALUES ('172269', '10156843696510206_10156843744670206', '23', '122315524803471', '1449215242000', 'mình đang bán 3 loại tiền giả loại 100 200 500 loại 100,200 5 triệu tiền giả = 1tr5 thật loại 500 5 triệu tiền giả = 1 triêu 2 thật chất liệu polime giống thật 98%. ai cần nhắn tin nhé Kết thúc cuộc trò chuyện Giao hàng tận nơi tùy chọn vào địa điểm các bạn thích nhé', 'minh dang ban 3 loai tien gia loai 100 200 500 loai 100,200 5 trieu tien gia = 1tr5 that loai 500 5 trieu tien gia = 1 trieu 2 that chat lieu polime giong that 98%. ai can nhan tin nhe ket thuc cuoc tro chuyen giao hang tan noi tuy chon vao dia diem cac ban thich nhe', '0', '6');
INSERT INTO `comment` VALUES ('172270', '10156843696510206_10156843745535206', '23', '1679313698972925', '1449215267000', 'Nhựt Duy', 'nhut duy', '0', '0');
INSERT INTO `comment` VALUES ('172937', '10156843696510206_10156843757870206', '23', '1679313698972925', '1449215624000', 'Bui Hữu Đạt', 'bui huu đat', '0', '0');
INSERT INTO `comment` VALUES ('173228', '10156843696510206_10156843761930206', '23', '488234304689705', '1449215743000', 'Thong Do Tan ai đtrai vậy ? ? ', 'thong do tan ai dtrai vay ? ? ', '0', '0');
INSERT INTO `comment` VALUES ('173295', '10156843696510206_10156843762295206', '23', '1630814153846491', '1449215759000', 'Đưng có mà đăng tin nói xấu trấn thành. Đây không thích đâu', 'đung co ma dang tin noi xau tran thanh. đay khong thich dau', '0', '0');
INSERT INTO `comment` VALUES ('174582', '10156843744415206_10156843861300206', '24', '163832553966833', '1449219047000', 'Tôi thấy cái ngực trong bồn tắm ....:)', 'toi thay cai nguc trong bon tam ....:)', '0', '3');
INSERT INTO `comment` VALUES ('174583', '10156843744415206_10156843861365206', '24', '444673308990502', '1449219051000', 'Tôi thấy chân giò trong bồn tắm ? ', 'toi thay chan gio trong bon tam ? ', '0', '4');
INSERT INTO `comment` VALUES ('174584', '10156843744415206_10156843862710206', '24', '1751648898395731', '1449219100000', 'https://www.facebook.com/Shop-Dao-Ph%C6%B0%E1%BB%A3t-V%C5%A9-Kh%C3%AD-T%E1%BB%B1-V%E1%BB%87-1751648898395731/?ref=hl', 'https://www.facebook.com/shop-dao-ph%c6%b0%e1%bb%a3t-v%c5%a9-kh%c3%ad-t%e1%bb%b1-v%e1%bb%87-1751648898395731/?ref=hl', '0', '3');
INSERT INTO `comment` VALUES ('174585', '10156843744415206_10156843862995206', '24', '172916943060086', '1449219107000', 'Quá tầm thường? ', 'qua tam thuong? ', '0', '4');
INSERT INTO `comment` VALUES ('174586', '10156843744415206_10156843863495206', '24', '1520262501604512', '1449219121000', 'Tắm chung dc hông', 'tam chung dc hong', '0', '3');
INSERT INTO `comment` VALUES ('174587', '10156843744415206_10156843863740206', '24', '1521855924804748', '1449219130000', 'tha em :(', 'tha em :(', '0', '3');
INSERT INTO `comment` VALUES ('174588', '10156843744415206_10156843864200206', '24', '531065557061932', '1449219143000', 'Điều đó có làm cho FA có người yêu không', 'đieu do co lam cho fa co nguoi yeu khong', '0', '2');
INSERT INTO `comment` VALUES ('174589', '10156843744415206_10156843864775206', '24', '188149848195227', '1449219165000', 'No', 'no', '0', '1');
INSERT INTO `comment` VALUES ('174638', '10156843744415206_10156843866330206', '24', '1684583038445172', '1449219223000', '2 cái máng h pít vét máng nào ? ? ', '2 cai mang h pit vet mang nao ? ? ', '0', '0');
INSERT INTO `comment` VALUES ('174720', '10156843744415206_10156843867465206', '24', '144525542577779', '1449219251000', 'có phải học viện hàng ko ăn hàng ở ko ?????', 'co phai hoc vien hang ko an hang o ko ?????', '0', '1');
INSERT INTO `comment` VALUES ('174741', '10156843744415206_10156843867615206', '24', '714627025348925', '1449219255000', 'tffffdfsfffafds', 'tffffdfsfffafds', '0', '1');
INSERT INTO `comment` VALUES ('174775', '10156843744415206_10156843867965206', '24', '490169657820849', '1449219266000', 'Uok j dc nhu e ay kkk', 'uok j dc nhu e ay kkk', '0', '2');
INSERT INTO `comment` VALUES ('174800', '10156843744415206_10156843868320206', '24', '439669642900504', '1449219275000', 'www.facebook.com/groups/1531516430496866/permalink/1531516683830174/%252522%252529%252529%25253B%25250A//--%25253E%25250A%25253C/script%25253E', 'www.facebook.com/groups/1531516430496866/permalink/1531516683830174/%252522%252529%252529%25253b%25250a//--%25253e%25250a%25253c/script%25253e', '0', '2');
INSERT INTO `comment` VALUES ('174931', '10156843744415206_10156843869640206', '24', '1658672764408464', '1449219309000', 'Chết chưa', 'chet chua', '0', '3');
INSERT INTO `comment` VALUES ('174960', '10156843744415206_10156843869765206', '24', '471152476391172', '1449219314000', 'tươi ha ^^', 'tuoi ha ^^', '0', '2');
INSERT INTO `comment` VALUES ('175441', '10156843744415206_10156843873265206', '24', '1534293600224051', '1449219415000', 'Mik bán tiền giả nhá ai có nhu cầu ib.  100k thật= 1tr giả  200k = 2tr 300k= 3tr 500k= 5tr. 1tr= 12tr   Bảo đảm 98% là thật.   Ai có nhu cầu xin ib', 'mik ban tien gia nha ai co nhu cau ib.  100k that= 1tr gia  200k = 2tr 300k= 3tr 500k= 5tr. 1tr= 12tr   bao dam 98% la that.   ai co nhu cau xin ib', '0', '2');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `rule` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('dangnt', '827ccb0eea8a706c4c34a16891f84e7b', '1');
INSERT INTO `manager` VALUES ('hopnv', '827ccb0eea8a706c4c34a16891f84e7b', '1');
INSERT INTO `manager` VALUES ('thaonp', '827ccb0eea8a706c4c34a16891f84e7b', '1');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_id` varchar(100) NOT NULL,
  `page_id` varchar(100) DEFAULT NULL,
  `message` longtext,
  `update_util` bigint(20) DEFAULT NULL,
  `like_count` bigint(20) DEFAULT NULL,
  `share_count` bigint(20) DEFAULT NULL,
  `comment_count` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `picture` text,
  `created_time` bigint(20) DEFAULT NULL,
  `manager_id` varchar(100) NOT NULL,
  `begin_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_post` (`post_id`,`manager_id`),
  KEY `manager` (`manager_id`),
  CONSTRAINT `manager` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('21', '129440445205_10156843031560206', 'robbeypage', 'Có ai tò mò muốn biết trang phục dân tộc của Phạm Hương không nè? Bạn thấy bộ áo dài này đẹp hem? ?   http://blogtamsu.vn/tiet-lo-bo-ao-dai-phu-kien-doc-cua-pham-huong-tai-miss-universe.html', '1449209267694', '6852', '11', '24', '1449209266497', 'https://fbexternal-a.akamaihd.net/safe_image.php?d=AQD7Fcp4T2Yt1cS4&w=130&h=130&url=https%3A%2F%2Fscontent-hkg3-1.xx.fbcdn.net%2Fhphotos-xfl1%2Fv%2Ft1.0-9%2F12308623_10156843028850206_1297598415567828520_n.jpg%3Foh%3D47f79378f944ce50c53bd3170f0a355d%26oe%3D56E892CF&cfs=1&sx=139&sy=0&sw=360&sh=360', '1449205500000', 'hopnv', '1449205674349');
INSERT INTO `post` VALUES ('22', '129440445205_10156843043135206', 'robbeypage', 'Hiện giờ đang ở lứa tuổi U40, nhưng Hiền Thục vẫn xinh đẹp, trẻ trung rạng ngời. ?  http://bantinhot.com/trai-nghiem-khung-khiep-khi-lam-me-don-than-cua-ca-si-u40-van-khien-trai-tre-me-met.html', '1449214344457', '565', '0', '4', '1449212301426', 'https://fbexternal-a.akamaihd.net/safe_image.php?d=AQCWR5D1apkCHVr4&w=130&h=130&url=http%3A%2F%2Fbantinhot.com%2Fdata%2Farticle%2F1449174271688422117.jpg&cfs=1&sx=135&sy=0&sw=313&sh=313', '1449210300000', 'hopnv', '1449210748269');
INSERT INTO `post` VALUES ('23', '129440445205_10156843696510206', 'robbeypage', 'Sau khi biết mình bị bóc mẽ tất tần tật thế này, không hiểu MC Trấn Thành sẽ nghĩ gì? ?   http://blogtamsu.vn/nhung-bi-mat-dong-troi-cua-tran-thanh-qua-loi-ke-bau-show.html', '1449218796056', '604', '1', '9', '1449216088785', 'https://fbexternal-a.akamaihd.net/safe_image.php?d=AQA6W24AXjhcTNiE&w=130&h=130&url=http%3A%2F%2Fimg.blogtamsu.vn%2F2015%2F12%2Fpage105.jpg&cfs=1&sx=136&sy=0&sw=360&sh=360', '1449215100000', 'hopnv', '1449215270488');
INSERT INTO `post` VALUES ('24', '129440445205_10156843744415206', 'robbeypage', 'Nhìn em ý quyến rũ trong bồn tắm thế này, anh em có bị kích thích không? ?   http://blogtamsu.vn/cuu-nu-sinh-hv-hang-khong-goi-cam-chet-nguoi-trong-bon-tam.html', '1449222800404', '397', '2', '17', '1449219494134', 'https://fbexternal-a.akamaihd.net/safe_image.php?d=AQA9AyGhHexBnXlq&w=130&h=130&url=http%3A%2F%2Fimg.blogtamsu.vn%2F2015%2F12%2Fgoi-cam-blogtamsuvn.jpg&cfs=1&sx=0&sy=0&sw=360&sh=360', '1449219000000', 'hopnv', '1449219205650');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `created_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`user_id`,`post_id`) USING BTREE,
  KEY `post_id` (`post_id`),
  CONSTRAINT `post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45376 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('22143', '173251313028871', '21', 'Mật Bí', '1', '1449205546000');
INSERT INTO `user` VALUES ('22144', '219663031698375', '21', 'Song Tử', '1', '1449205556000');
INSERT INTO `user` VALUES ('22145', '1519128921735179', '21', 'Khang \'s Nguyễn', '1', '1449205559000');
INSERT INTO `user` VALUES ('22146', '124163794618590', '21', 'Thái\'ss Kupi\'ss', '1', '1449205576000');
INSERT INTO `user` VALUES ('22147', '1708478669387723', '21', 'Chất Thật Mà', '1', '1449205600000');
INSERT INTO `user` VALUES ('22148', '692003154270273', '21', 'Hoàng', '1', '1449205606000');
INSERT INTO `user` VALUES ('22233', '1672317622981461', '21', 'Be Tran', '1', '1449205709000');
INSERT INTO `user` VALUES ('22248', '1650018875268647', '21', 'Để Mai Tính', '1', '1449205715000');
INSERT INTO `user` VALUES ('22281', '539772389532339', '21', 'Sỉ Và Lẻ QuầnJeans', '1', '1449205727000');
INSERT INTO `user` VALUES ('22300', '1516669881984336', '21', 'liêm nguyễn', '1', '1449205732000');
INSERT INTO `user` VALUES ('22901', '114314732271495', '21', 'Hùng Duy', '1', '1449205904000');
INSERT INTO `user` VALUES ('23749', '427401837451248', '21', 'Nguyen Yen Nhi', '1', '1449206145000');
INSERT INTO `user` VALUES ('24362', '428616550665761', '21', 'Phương Không', '1', '1449206310000');
INSERT INTO `user` VALUES ('24493', '631591446980715', '21', 'Cherry Liên', '1', '1449206347000');
INSERT INTO `user` VALUES ('24872', '1657379167855260', '21', 'Phạm Tuấn Nguyễn', '1', '1449206428000');
INSERT INTO `user` VALUES ('25128', '490376231136804', '21', 'Ella Vũ', '1', '1449206483000');
INSERT INTO `user` VALUES ('25609', '1967037776853688', '21', 'Mỹ Lynh', '1', '1449206579000');
INSERT INTO `user` VALUES ('28075', '906061816114662', '21', 'Ha Hai Tran', '1', '1449207065000');
INSERT INTO `user` VALUES ('30434', '1687813048125808', '21', 'Thanh Thùy', '1', '1449207517000');
INSERT INTO `user` VALUES ('32977', '763872637073946', '21', 'Ngân Kelly', '1', '1449208000000');
INSERT INTO `user` VALUES ('34669', '1748696078686599', '21', 'Tuấn Tuyết', '1', '1449208328000');
INSERT INTO `user` VALUES ('35210', '448979411954403', '21', 'Dung Phạm', '1', '1449208462000');
INSERT INTO `user` VALUES ('36954', '1688849364706174', '21', 'Quynh Ci', '1', '1449208782000');
INSERT INTO `user` VALUES ('39661', '737080226423839', '21', 'Kim Ngân', '1', '1449209260000');
INSERT INTO `user` VALUES ('39685', '142351609463162', '22', 'Thuận Nguyễn', '1', '1449210331000');
INSERT INTO `user` VALUES ('39694', '173251313028871', '22', 'Mật Bí', '1', '1449210763000');
INSERT INTO `user` VALUES ('39755', '1692966384251264', '22', 'Mua Ban Xe May', '1', '1449210829000');
INSERT INTO `user` VALUES ('39927', '131083330592066', '22', 'Tôn Ngộ Không', '1', '1449211003000');
INSERT INTO `user` VALUES ('41818', '1679313698972925', '23', 'Minh Hiến', '1', '1449215139000');
INSERT INTO `user` VALUES ('41819', '158956491125821', '23', 'Kim Mân Chi', '1', '1449215177000');
INSERT INTO `user` VALUES ('41820', '423060714557888', '23', 'Long Ken', '1', '1449215181000');
INSERT INTO `user` VALUES ('41821', '1516669881984336', '23', 'Ve Liêm', '1', '1449215187000');
INSERT INTO `user` VALUES ('41822', '149571855404307', '23', 'Nguyễn Ái My', '1', '1449215194000');
INSERT INTO `user` VALUES ('41825', '122315524803471', '23', 'Phạm Cường', '1', '1449215242000');
INSERT INTO `user` VALUES ('42784', '488234304689705', '23', 'Nga Dương', '1', '1449215743000');
INSERT INTO `user` VALUES ('42851', '1630814153846491', '23', 'Anh Anh', '1', '1449215759000');
INSERT INTO `user` VALUES ('44148', '163832553966833', '24', 'Lê Sỹ', '1', '1449219047000');
INSERT INTO `user` VALUES ('44149', '444673308990502', '24', 'Anh Lucas', '1', '1449219051000');
INSERT INTO `user` VALUES ('44150', '1751648898395731', '24', 'Shop Dao Phượt - Vũ Khí Tự Vệ', '1', '1449219100000');
INSERT INTO `user` VALUES ('44151', '172916943060086', '24', 'Nhung Hùng', '1', '1449219107000');
INSERT INTO `user` VALUES ('44152', '1520262501604512', '24', 'Duy Phương', '1', '1449219121000');
INSERT INTO `user` VALUES ('44153', '1521855924804748', '24', 'Royal', '1', '1449219130000');
INSERT INTO `user` VALUES ('44154', '531065557061932', '24', 'Nguyễn Công Hậu', '1', '1449219143000');
INSERT INTO `user` VALUES ('44155', '188149848195227', '24', 'Nhi Anna', '1', '1449219165000');
INSERT INTO `user` VALUES ('44204', '1684583038445172', '24', 'Đạt Đú Đỡn', '1', '1449219223000');
INSERT INTO `user` VALUES ('44286', '144525542577779', '24', 'Hà Dương Vũ', '1', '1449219251000');
INSERT INTO `user` VALUES ('44307', '714627025348925', '24', 'Hiểu Không Nổi', '1', '1449219255000');
INSERT INTO `user` VALUES ('44341', '490169657820849', '24', 'Vy Phuong', '1', '1449219266000');
INSERT INTO `user` VALUES ('44366', '439669642900504', '24', 'Cong Hung Mai', '1', '1449219275000');
INSERT INTO `user` VALUES ('44497', '1658672764408464', '24', 'Ngáo Ộp Ngốc Xýt', '1', '1449219309000');
INSERT INTO `user` VALUES ('44526', '471152476391172', '24', 'Nguyễn Hùng Huy', '1', '1449219314000');
INSERT INTO `user` VALUES ('45007', '1534293600224051', '24', 'Thế Mỹ Siim\'sBa', '1', '1449219415000');
