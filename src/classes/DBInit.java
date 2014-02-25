package classes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import classes.DBHandlers.*;
import classes.DBTables.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 04.02.14
 * Time: 17:22
 */
public class DBInit {
    public static void Mkb10_Proba_Init(Context context, SQLiteDatabase db) {
        if (!isTableExists(context, db, Mkb10_Proba_DBHandler.TABLE_NAME)) {
            Mkb10_Proba_DBHandler mkb10DBHandler = new Mkb10_Proba_DBHandler(context);
            //mkb10DBHandler.onUpgrade(db,1,1);
            mkb10DBHandler.onCreate(db);
            mkb10DBHandler.addItem(new Mkb10_Proba(1, 0, "A00-B99", "Некоторые инфекционные и паразитарные болезни"));
            mkb10DBHandler.addItem(new Mkb10_Proba(2, 1, "A00-A09", "Кишечные инфекции"));
            mkb10DBHandler.addItem(new Mkb10_Proba(3, 2, "A00", "Холера"));
            mkb10DBHandler.addItem(new Mkb10_Proba(4, 3, "A00.0", "Холера, вызванная холерным вибрионом 01, биовар cholerae"));
            mkb10DBHandler.addItem(new Mkb10_Proba(5, 3, "A00.1", "Холера, вызванная холерным вибрионом 01, биовар eltor"));
            mkb10DBHandler.addItem(new Mkb10_Proba(6, 3, "A00.9", "Холера неуточненная"));
            mkb10DBHandler.addItem(new Mkb10_Proba(7, 2, "A01", "Тиф и паратиф"));
            mkb10DBHandler.addItem(new Mkb10_Proba(8, 7, "A01.0", "Брюшной тиф"));
            mkb10DBHandler.addItem(new Mkb10_Proba(9, 7, "A01.1", "Паратиф a"));
            mkb10DBHandler.addItem(new Mkb10_Proba(10, 7, "A01.2", "Паратиф b"));
            mkb10DBHandler.addItem(new Mkb10_Proba(11, 7, "A01.3", "Паратиф c"));
            mkb10DBHandler.addItem(new Mkb10_Proba(12, 7, "A01.4", "Паратиф неуточненный"));
            mkb10DBHandler.addItem(new Mkb10_Proba(13, 2, "A02", "Другие сальмонеллезные инфекции"));
            mkb10DBHandler.addItem(new Mkb10_Proba(14, 13, "A02.0", "Сальмонеллезный энтерит"));
            mkb10DBHandler.addItem(new Mkb10_Proba(15, 13, "A02.1", "Сальмонеллезная септицемия"));
            mkb10DBHandler.addItem(new Mkb10_Proba(16, 13, "A02.2+", "Локализованная сальмонеллезная инфекция"));
            mkb10DBHandler.addItem(new Mkb10_Proba(17, 13, "A02.8", "Другая уточненная сальмонеллезная инфекция"));
            mkb10DBHandler.addItem(new Mkb10_Proba(18, 13, "A02.9", "Сальмонеллезная инфекция неуточненная"));
            mkb10DBHandler.addItem(new Mkb10_Proba(19, 2, "A03", "Шигеллез"));
            mkb10DBHandler.addItem(new Mkb10_Proba(20, 19, "A03.0", "Шигеллез, вызванный shigella dysenteriae"));
            mkb10DBHandler.addItem(new Mkb10_Proba(21, 19, "A03.1", "Шигеллез, вызванный shigella flexneri"));
            mkb10DBHandler.addItem(new Mkb10_Proba(22, 19, "A03.2", "Шигеллез, вызванный shigella boydii"));
            mkb10DBHandler.addItem(new Mkb10_Proba(23, 19, "A03.3", "Шигеллез, вызванный shigella sonnei"));
            mkb10DBHandler.addItem(new Mkb10_Proba(24, 19, "A03.8", "Другой шигеллез"));
            mkb10DBHandler.addItem(new Mkb10_Proba(25, 19, "A03.9", "Шигеллез неуточненный"));
            mkb10DBHandler.addItem(new Mkb10_Proba(26, 2, "A04", "Другие бактериальные кишечные инфекции"));
            mkb10DBHandler.addItem(new Mkb10_Proba(27, 26, "A04.0", "Энтеропатогенная инфекция, вызванная escherichia coli"));
            mkb10DBHandler.addItem(new Mkb10_Proba(28, 26, "A04.1", "Энтеротоксигенная инфекция, вызванная escherichia coli"));
            mkb10DBHandler.addItem(new Mkb10_Proba(29, 26, "A04.2", "Энтероинвазивная инфекция, вызванная escherichia coli"));
            mkb10DBHandler.addItem(new Mkb10_Proba(30, 26, "A04.3", "Энтерогеморрагическая инфекция, вызванная escherichia coli"));
            mkb10DBHandler.addItem(new Mkb10_Proba(31, 26, "A04.4", "Другие кишечные инфекции, вызванные escherichia coli"));
            mkb10DBHandler.addItem(new Mkb10_Proba(32, 26, "A04.5", "Энтерит, вызванный campylobacter"));
            mkb10DBHandler.addItem(new Mkb10_Proba(33, 26, "A04.6", "Энтерит, вызванный yersinia enterocolitica"));
            mkb10DBHandler.addItem(new Mkb10_Proba(34, 26, "A04.7", "Энтероколит, вызванный clostridium difficile"));
            mkb10DBHandler.addItem(new Mkb10_Proba(35, 26, "A04.8", "Другие уточненные бактериальные кишечные инфекции"));
            mkb10DBHandler.addItem(new Mkb10_Proba(36, 26, "A04.9", "Бактериальная кишечная инфекция неуточненная"));
            mkb10DBHandler.addItem(new Mkb10_Proba(37, 2, "A05", "Другие бактериальные пищевые отравления"));
            mkb10DBHandler.addItem(new Mkb10_Proba(38, 37, "A05.0", "Стафилококковое пищевое отравление"));
            mkb10DBHandler.addItem(new Mkb10_Proba(39, 37, "A05.1", "Ботулизм"));
            mkb10DBHandler.addItem(new Mkb10_Proba(40, 37, "A05.2", "Пищевое отравление, вызванное clostridium perfringens"));
            mkb10DBHandler.addItem(new Mkb10_Proba(41, 37, "A05.3", "Пищевое отравление, вызванное vibrio parahaemolyticus"));
            mkb10DBHandler.addItem(new Mkb10_Proba(42, 37, "A05.4", "Пищевое отравление, вызванное bacillus cereus"));
            mkb10DBHandler.addItem(new Mkb10_Proba(43, 37, "A05.8", "Другие уточненные бактериальные пищевые отравления"));
            mkb10DBHandler.addItem(new Mkb10_Proba(44, 37, "A05.9", "Бактериальное пищевое отравление неуточненное"));
            mkb10DBHandler.addItem(new Mkb10_Proba(45, 2, "A06", "Амебиаз"));
            mkb10DBHandler.addItem(new Mkb10_Proba(46, 45, "A06.0", "Острая амебная дизентерия"));
            mkb10DBHandler.addItem(new Mkb10_Proba(47, 45, "A06.1", "Хронический кишечный амебиаз"));
            mkb10DBHandler.addItem(new Mkb10_Proba(48, 45, "A06.2", "Амебный недизентерийный колит"));
            mkb10DBHandler.addItem(new Mkb10_Proba(49, 45, "A06.3", "Амебома кишечника"));
            mkb10DBHandler.addItem(new Mkb10_Proba(50, 45, "A06.4", "Амебный абсцесс печени"));
            mkb10DBHandler.addItem(new Mkb10_Proba(51, 45, "A06.5+", "Амебный абсцесс легкого mkb10DBHandler.addItem(new Mkb10_Proba(j99.8*)"));
            mkb10DBHandler.addItem(new Mkb10_Proba(52, 45, "A06.6+", "Амебный абсцесс головного мозга (g07*)"));
            mkb10DBHandler.addItem(new Mkb10_Proba(53, 45, "A06.7", "Кожный амебиаз"));
            mkb10DBHandler.addItem(new Mkb10_Proba(54, 45, "A06.8", "Амебная инфекция другой локализации"));
            mkb10DBHandler.addItem(new Mkb10_Proba(55, 45, "A06.9", "Амебиаз неуточненный"));
            mkb10DBHandler.addItem(new Mkb10_Proba(56, 2, "A07", "Другие протозойные кишечные болезни"));
            mkb10DBHandler.addItem(new Mkb10_Proba(57, 56, "A07.0", "Балантидиаз"));
            mkb10DBHandler.addItem(new Mkb10_Proba(58, 56, "A07.1", "Жиардиаз [лямблиоз]"));
            mkb10DBHandler.addItem(new Mkb10_Proba(59, 56, "A07.2", "Криптоспоридиоз"));
            mkb10DBHandler.addItem(new Mkb10_Proba(60, 56, "A07.3", "Изоспороз"));
            mkb10DBHandler.addItem(new Mkb10_Proba(61, 56, "A07.8", "Другие уточненные протозойные кишечные болезни"));
            mkb10DBHandler.addItem(new Mkb10_Proba(62, 56, "A07.9", "Протозойная кишечная болезнь неуточненная"));
            mkb10DBHandler.addItem(new Mkb10_Proba(63, 2, "A08", "Вирусные и другие уточненные кишечные инфекции"));
            mkb10DBHandler.addItem(new Mkb10_Proba(64, 63, "A08.0", "Ротавирусный энтерит"));
            mkb10DBHandler.addItem(new Mkb10_Proba(65, 63, "A08.1", "Острая гастроэнтеропатия, вызванная возбудителем норволк"));
            mkb10DBHandler.addItem(new Mkb10_Proba(66, 63, "A08.2", "Аденовирусный энтерит"));
            mkb10DBHandler.addItem(new Mkb10_Proba(67, 63, "A08.3", "Другие вирусные энтериты"));
            mkb10DBHandler.addItem(new Mkb10_Proba(68, 63, "A08.4", "Вирусная кишечная инфекция неуточненная"));
            mkb10DBHandler.addItem(new Mkb10_Proba(69, 63, "A08.5", "Другие уточненные кишечные инфекции"));
            mkb10DBHandler.addItem(new Mkb10_Proba(70, 2, "A09", "Диарея и гастроэнтерит предположительно инфекционного происхождения"));
            mkb10DBHandler.addItem(new Mkb10_Proba(72, 1, "A15-A19", "Туберкулез"));
            mkb10DBHandler.addItem(new Mkb10_Proba(73, 72, "A15", "Туберкулез органов дыхания, подтвержденный бактериологически и гистологически"));
            mkb10DBHandler.addItem(new Mkb10_Proba(74, 73, "A15.0", "Туберкулез легких, подтвержденный бактериоскопически с наличием или отсутствием роста культуры"));
            mkb10DBHandler.addItem(new Mkb10_Proba(75, 73, "A15.1", "Туберкулез легких, подтвержденный только ростом культуры"));
            mkb10DBHandler.addItem(new Mkb10_Proba(76, 73, "A15.2", "Туберкулез легких, подтвержденный гистологически"));
            mkb10DBHandler.addItem(new Mkb10_Proba(77, 73, "A15.3", "Туберкулез легких, подтвержденный неуточненными методами"));
            mkb10DBHandler.addItem(new Mkb10_Proba(78, 73, "A15.4", "Туберкулез внутригрудных лимфатических узлов, подтвержденный бактериологически и гистологически"));
            mkb10DBHandler.addItem(new Mkb10_Proba(79, 73, "A15.5", "Туберкулез гортани, трахеи и бронхов, подтвержденный бактериологически и гистологически"));
            mkb10DBHandler.addItem(new Mkb10_Proba(80, 73, "A15.6", "Туберкулезный плеврит, подтвержденный бактериологически и гистологически"));
            mkb10DBHandler.addItem(new Mkb10_Proba(81, 73, "A15.7", "Первичный туберкулез органов дыхания, подтвержденный бактериологически и гистологически"));
            mkb10DBHandler.addItem(new Mkb10_Proba(82, 73, "A15.8", "Туберкулез других органов дыхания, подтвержденный бактериологически и гистологически"));
            mkb10DBHandler.addItem(new Mkb10_Proba(83, 73, "A15.9", "Туберкулез органов дыхания неуточненной локализации, подтвержденный бактериологически и гистологически"));
            mkb10DBHandler.addItem(new Mkb10_Proba(84, 72, "A16", "Туберкулез органов дыхания, не подтвержденный бактериологически или гистологически"));
            mkb10DBHandler.addItem(new Mkb10_Proba(85, 84, "A16.0", "Туберкулез легких при отрицательных результатах бактериологических и гистологических исследований"));
            mkb10DBHandler.addItem(new Mkb10_Proba(86, 84, "A16.1", "Туберкулез легких без проведения бактериологического и гистологического исследований"));
            mkb10DBHandler.addItem(new Mkb10_Proba(87, 84, "A16.2", "Туберкулез легких без упоминания о бактериологическом или гистологическом подтверждении"));
            mkb10DBHandler.addItem(new Mkb10_Proba(88, 84, "A16.3", "Туберкулез внутригрудных лимфатических узлов без упоминания о бактериологическом или гистологическом подтверждении"));
            mkb10DBHandler.addItem(new Mkb10_Proba(89, 84, "A16.4", "Туберкулез гортани, трахеи и бронхов без упоминания о бактериологическом или гистологическом подтверждении"));
            mkb10DBHandler.addItem(new Mkb10_Proba(90, 84, "A16.5", "Туберкулезный плеврит без упоминания о бактериологическом или гистологическом подтверждении"));
            mkb10DBHandler.addItem(new Mkb10_Proba(91, 84, "A16.7", "Первичный туберкулез органов дыхания без упоминания о бактериологическом или гистологическом подтверждении"));
            mkb10DBHandler.addItem(new Mkb10_Proba(92, 84, "A16.8", "Туберкулез других органов дыхания без упоминания о бактериологическом или гистологическом подтверждении"));
            mkb10DBHandler.addItem(new Mkb10_Proba(93, 84, "A16.9", "Туберкулез органов дыхания неуточненной локализации без упоминания о бактериологическом или гистологическом подтверждении"));
            mkb10DBHandler.addItem(new Mkb10_Proba(94, 72, "A17+", "Туберкулез нервной системы"));
            mkb10DBHandler.addItem(new Mkb10_Proba(95, 94, "A17.0+", "Туберкулезный менингит (g01*)"));
            mkb10DBHandler.addItem(new Mkb10_Proba(96, 94, "A17.1+", "Менингеальная туберкулема (g07*)"));
            mkb10DBHandler.addItem(new Mkb10_Proba(97, 94, "A17.8+", "Туберкулез нервной системы других локализаций"));
            mkb10DBHandler.addItem(new Mkb10_Proba(98, 94, "A17.9+", "Туберкулез нервной системы неуточненный (g99.8*)"));
            mkb10DBHandler.addItem(new Mkb10_Proba(99, 72, "A18", "Туберкулез других органов"));
            mkb10DBHandler.addItem(new Mkb10_Proba(100, 99, "A18.0+", "Туберкулез костей и суставов"));
            mkb10DBHandler.addItem(new Mkb10_Proba(101, 99, "A18.1+", "Туберкулез мочеполовых органов"));
            mkb10DBHandler.addItem(new Mkb10_Proba(102, 99, "A18.2", "Туберкулезная периферическая лимфаденопатия"));
            mkb10DBHandler.addItem(new Mkb10_Proba(103, 99, "A18.3", "Туберкулез кишечника, брюшины и брыжеечных лимфатических узлов"));
            mkb10DBHandler.addItem(new Mkb10_Proba(104, 99, "A18.4", "Туберкулез кожи и подкожной клетчатки"));
            mkb10DBHandler.addItem(new Mkb10_Proba(105, 99, "A18.5+", "Туберкулез глаза"));
            mkb10DBHandler.addItem(new Mkb10_Proba(106, 99, "A18.6+", "Туберкулез уха"));
            mkb10DBHandler.addItem(new Mkb10_Proba(107, 99, "A18.7+", "Туберкулез надпочечников (e35.1*)"));
            mkb10DBHandler.addItem(new Mkb10_Proba(108, 99, "A18.8+", "Туберкулез других уточненных органов"));
            mkb10DBHandler.addItem(new Mkb10_Proba(109, 72, "A19", "Милиарный туберкулез"));
            mkb10DBHandler.addItem(new Mkb10_Proba(110, 109, "A19.0", "Острый милиарный туберкулез одной уточненной локализации"));
            mkb10DBHandler.addItem(new Mkb10_Proba(111, 109, "A19.1", "Острый милиарный туберкулез множественной локализации"));
            mkb10DBHandler.addItem(new Mkb10_Proba(112, 109, "A19.2", "Острый милиарный туберкулез неуточненной локализации"));
            mkb10DBHandler.addItem(new Mkb10_Proba(113, 109, "A19.8", "Другие формы милиарного туберкулеза"));
            mkb10DBHandler.addItem(new Mkb10_Proba(114, 109, "A19.9", "Милиарный туберкулез неуточненной локализации"));
            mkb10DBHandler.addItem(new Mkb10_Proba(116, 1, "A20-A28", "Некоторые бактериальные зоонозы"));
            mkb10DBHandler.addItem(new Mkb10_Proba(117, 116, "A20", "Чума"));
            mkb10DBHandler.addItem(new Mkb10_Proba(118, 117, "A20.0", "Бубонная чума"));
            mkb10DBHandler.addItem(new Mkb10_Proba(119, 117, "A20.1", "Целлюлярнокожная чума"));
            mkb10DBHandler.addItem(new Mkb10_Proba(120, 117, "A20.2", "Легочная чума"));
            mkb10DBHandler.addItem(new Mkb10_Proba(121, 117, "A20.3", "Чумной менингит"));
            mkb10DBHandler.addItem(new Mkb10_Proba(122, 117, "A20.7", "Септическая чума"));
            mkb10DBHandler.addItem(new Mkb10_Proba(123, 117, "A20.8", "Другие формы чумы"));
            mkb10DBHandler.addItem(new Mkb10_Proba(124, 117, "A20.9", "Чума неуточненная"));
            mkb10DBHandler.addItem(new Mkb10_Proba(125, 116, "A21", "Туляремия"));
            mkb10DBHandler.addItem(new Mkb10_Proba(126, 125, "A21.0", "Ульцерогландулярная туляремия"));
            mkb10DBHandler.addItem(new Mkb10_Proba(127, 125, "A21.1", "Окулогландулярная туляремия"));
            mkb10DBHandler.addItem(new Mkb10_Proba(128, 125, "A21.2", "Легочная туляремия"));
            mkb10DBHandler.addItem(new Mkb10_Proba(129, 125, "A21.3", "Желудочно-кишечная туляремия"));
            mkb10DBHandler.addItem(new Mkb10_Proba(130, 125, "A21.7", "Генерализованная туляремия"));
            mkb10DBHandler.addItem(new Mkb10_Proba(131, 125, "A21.8", "Другие формы туляремии"));
            mkb10DBHandler.addItem(new Mkb10_Proba(132, 125, "A21.9", "Туляремия неуточненная"));
            mkb10DBHandler.addItem(new Mkb10_Proba(133, 116, "A22", "Сибирская язва"));
            mkb10DBHandler.addItem(new Mkb10_Proba(134, 133, "A22.0", "Кожная форма сибирской язвы"));
            mkb10DBHandler.addItem(new Mkb10_Proba(135, 133, "A22.1", "Легочная форма сибирской язвы"));
            mkb10DBHandler.addItem(new Mkb10_Proba(136, 133, "A22.2", "Желудочно-кишечная форма сибирской язвы"));
            mkb10DBHandler.addItem(new Mkb10_Proba(137, 133, "A22.7", "Сибиреязвенная септицемия"));
            mkb10DBHandler.addItem(new Mkb10_Proba(138, 133, "A22.8", "Другие формы сибирской язвы"));
            mkb10DBHandler.addItem(new Mkb10_Proba(139, 133, "A22.9", "Сибирская язва неуточненная"));
            mkb10DBHandler.addItem(new Mkb10_Proba(140, 116, "A23", "Бруцеллез"));
            mkb10DBHandler.addItem(new Mkb10_Proba(141, 140, "A23.0", "Бруцеллез, вызванный brucella melitensis"));
            mkb10DBHandler.addItem(new Mkb10_Proba(142, 140, "A23.1", "Бруцеллез, вызванный brucella abortus"));
            mkb10DBHandler.addItem(new Mkb10_Proba(143, 140, "A23.2", "Бруцеллез, вызванный brucella suis"));
            mkb10DBHandler.addItem(new Mkb10_Proba(144, 140, "A23.3", "Бруцеллез, вызванный brucella canis"));
            mkb10DBHandler.addItem(new Mkb10_Proba(145, 140, "A23.8", "Другие формы бруцеллеза"));
            mkb10DBHandler.addItem(new Mkb10_Proba(146, 140, "A23.9", "Бруцеллез неуточненный"));
            mkb10DBHandler.addItem(new Mkb10_Proba(147, 116, "A24", "Сап и мелиоидоз"));
            mkb10DBHandler.addItem(new Mkb10_Proba(148, 147, "A24.0", "Сап"));
            mkb10DBHandler.addItem(new Mkb10_Proba(149, 147, "A24.1", "Острый или молниеносный мелиоидоз"));
            mkb10DBHandler.addItem(new Mkb10_Proba(150, 147, "A24.2", "Подострый и хронический мелиоидоз"));
            mkb10DBHandler.addItem(new Mkb10_Proba(151, 147, "A24.3", "Другой уточненный мелиоидоз"));
            mkb10DBHandler.addItem(new Mkb10_Proba(152, 147, "A24.4", "Мелиоидоз неуточненный"));
            mkb10DBHandler.addItem(new Mkb10_Proba(153, 116, "A25", "Лихорадка от укуса крыс"));
            mkb10DBHandler.addItem(new Mkb10_Proba(154, 153, "A25.0", "Спириллез"));
            mkb10DBHandler.addItem(new Mkb10_Proba(155, 153, "A25.1", "Стрептобациллез"));
            mkb10DBHandler.addItem(new Mkb10_Proba(156, 153, "A25.9", "Лихорадка от укуса крыс неуточненная"));
            mkb10DBHandler.addItem(new Mkb10_Proba(157, 116, "A26", "Эризипелоид"));
            mkb10DBHandler.addItem(new Mkb10_Proba(158, 157, "A26.0", "Кожный эризипелоид"));
            mkb10DBHandler.addItem(new Mkb10_Proba(159, 157, "A26.7", "Септицемия, вызванная erysipelothrix"));
            mkb10DBHandler.addItem(new Mkb10_Proba(160, 157, "A26.8", "Другие формы эризипелоида"));
            mkb10DBHandler.addItem(new Mkb10_Proba(161, 157, "A26.9", "Эризипелоид неуточненный"));
            mkb10DBHandler.addItem(new Mkb10_Proba(162, 116, "A27", "Лептоспироз"));
            mkb10DBHandler.addItem(new Mkb10_Proba(163, 162, "A27.0", "Лептоспироз желтушно-геморрагический"));
            mkb10DBHandler.addItem(new Mkb10_Proba(164, 162, "A27.8", "Другие формы лептоспироза"));
            mkb10DBHandler.addItem(new Mkb10_Proba(165, 162, "A27.9", "Лептоспироз неуточненный"));
            mkb10DBHandler.addItem(new Mkb10_Proba(166, 116, "A28", "Другие бактериальные зоонозы, не классифицированные в других рубриках"));
            mkb10DBHandler.addItem(new Mkb10_Proba(167, 166, "A28.0", "Пастереллез"));
            mkb10DBHandler.addItem(new Mkb10_Proba(168, 166, "A28.1", "Лихорадка от кошачьих царапин"));
            mkb10DBHandler.addItem(new Mkb10_Proba(169, 166, "A28.2", "Экстраинтестинальный иерсиниоз"));
            mkb10DBHandler.addItem(new Mkb10_Proba(170, 166, "A28.8", "Другие уточненные бактериальные зоонозы, не классифи-"));
            mkb10DBHandler.addItem(new Mkb10_Proba(171, 166, "A28.9", "Бактериальные зоонозы неуточненные"));
            mkb10DBHandler.addItem(new Mkb10_Proba(173, 1, "A30-A49", "Другие бактериальные болезни"));
            mkb10DBHandler.addItem(new Mkb10_Proba(174, 173, "A30", "Лепра [болезнь гансена]"));
            mkb10DBHandler.addItem(new Mkb10_Proba(175, 174, "A30.0", "Недифференцированная лепра"));
            mkb10DBHandler.addItem(new Mkb10_Proba(176, 174, "A30.1", "Туберкулоидная лепра"));
            mkb10DBHandler.addItem(new Mkb10_Proba(177, 174, "A30.2", "Пограничная туберкулоидная лепра"));
            mkb10DBHandler.addItem(new Mkb10_Proba(178, 174, "A30.3", "Пограничная лепра"));
            mkb10DBHandler.addItem(new Mkb10_Proba(179, 174, "A30.4", "Пограничная лепроматозная лепра"));
            mkb10DBHandler.addItem(new Mkb10_Proba(180, 174, "A30.5", "Лепроматозная лепра"));
            mkb10DBHandler.addItem(new Mkb10_Proba(181, 174, "A30.8", "Другие формы лепры"));
            mkb10DBHandler.addItem(new Mkb10_Proba(182, 174, "A30.9", "Лепра неуточненная"));
            mkb10DBHandler.addItem(new Mkb10_Proba(183, 173, "A31", "Инфекции, вызванные другими микобактериями"));
            mkb10DBHandler.addItem(new Mkb10_Proba(184, 183, "A31.0", "Легочная инфекция, вызванная mycobacterium"));
            mkb10DBHandler.addItem(new Mkb10_Proba(185, 183, "A31.1", "Кожная инфекция, вызванная mycobacterium"));
            mkb10DBHandler.addItem(new Mkb10_Proba(186, 183, "A31.8", "Другие инфекции, вызванные mycobacterium"));
            mkb10DBHandler.addItem(new Mkb10_Proba(187, 183, "A31.9", "Инфекция, вызванная mycobacterium, неуточненная"));
            mkb10DBHandler.addItem(new Mkb10_Proba(188, 173, "A32", "Листериоз"));
            mkb10DBHandler.addItem(new Mkb10_Proba(189, 188, "A32.0", "Кожный листериоз"));
            mkb10DBHandler.addItem(new Mkb10_Proba(190, 188, "A32.1+", "Листериозный менингит и менингоэнцефалит"));
        }
    }

    public static void DosageUnit_Init(Context context, SQLiteDatabase db) {
        if (!isTableExists(context, db, DosageUnit_DBHandler.TABLE_NAME)) {
            DosageUnit_DBHandler dosageUnitDBHandler = new DosageUnit_DBHandler(context);
            //dosageUnitDBHandler.onUpgrade(db,1,1);
            dosageUnitDBHandler.onCreate(db);
            dosageUnitDBHandler.addItem(new DosageUnit(1, "мг"));
            dosageUnitDBHandler.addItem(new DosageUnit(2, "г"));
            dosageUnitDBHandler.addItem(new DosageUnit(3, "мл"));
        }
    }

    public static void QuantityUnit_Init(Context context, SQLiteDatabase db) {
        if (!isTableExists(context, db, QuantityUnit_DBHandler.TABLE_NAME)) {
            QuantityUnit_DBHandler quantityUnitDBHandler = new QuantityUnit_DBHandler(context);
            //quantityUnitDBHandler.onUpgrade(db,1,1);
            quantityUnitDBHandler.onCreate(db);
            quantityUnitDBHandler.addItem(new QuantityUnit(1, "таблетка"));
            quantityUnitDBHandler.addItem(new QuantityUnit(2, "ампула"));
            quantityUnitDBHandler.addItem(new QuantityUnit(3, "ст. ложка"));
            quantityUnitDBHandler.addItem(new QuantityUnit(4, "мг"));
            quantityUnitDBHandler.addItem(new QuantityUnit(5, "ч. ложка"));
        }
    }

    public static void Medication_Init(Context context, SQLiteDatabase db) {
        if (!isTableExists(context, db, Medication_DBHandler.TABLE_NAME)) {
            Medication_DBHandler medicationDBHandler = new Medication_DBHandler(context);
            //medicationDBHandler.onUpgrade(db,1,1);
            medicationDBHandler.onCreate(db);
            medicationDBHandler.addItem(new Medication(1, 1, 100, 0.5, 1, 5, 1, 5, 1, CommonFunctions.getDateFromString(context, "2013-12-04", StaticVariables.dateFormat), CommonFunctions.getDateFromString(context, "2014-12-11", StaticVariables.dateFormat), 1, 1));
            medicationDBHandler.addItem(new Medication(2, 1, 101, 1, 1, 1, 10, 2, 1, CommonFunctions.getDateFromString(context, "2013-12-04", StaticVariables.dateFormat), CommonFunctions.getDateFromString(context, "2014-12-11", StaticVariables.dateFormat), 1, 1));
            medicationDBHandler.addItem(new Medication(3, 1, 102, 1.5, 1, 15, 1, 2, 1, CommonFunctions.getDateFromString(context, "2013-12-04", StaticVariables.dateFormat), CommonFunctions.getDateFromString(context, "2014-12-11", StaticVariables.dateFormat), 1, 1));
        }
    }

    public static void Recommendation_Init(Context context, SQLiteDatabase db) {
        if (!isTableExists(context, db, Recommendation_DBHandler.TABLE_NAME)) {
            Recommendation_DBHandler recommendationDBHandler = new Recommendation_DBHandler(context);
            //recommendationDBHandler.onUpgrade(db,1,1);
            recommendationDBHandler.onCreate(db);
            recommendationDBHandler.addItem(new Recommendation(1, 1, CommonFunctions.getDateFromString(context, "2013-12-04", StaticVariables.dateFormat), 1));
            recommendationDBHandler.addItem(new Recommendation(1, 1, CommonFunctions.getDateFromString(context, "2013-12-04", StaticVariables.dateFormat), 1));
            recommendationDBHandler.addItem(new Recommendation(1, 1, CommonFunctions.getDateFromString(context, "2013-12-04", StaticVariables.dateFormat), 1));
        }
    }

    public static void Specialist_Init(Context context, SQLiteDatabase db) {
        if (!isTableExists(context, db, Specialist_DBHandler.TABLE_NAME)) {
            Specialist_DBHandler specialistDBHandler = new Specialist_DBHandler(context);
            specialistDBHandler.onCreate(db);
            specialistDBHandler.addItem(new Specialist(1,
                    "Иван",
                    "Иванов",
                    "терапевт",
                    "Алматы",
                    "antonkraskov@gmail.com",
                    "+77051944323",
                    2,
                    "bfbde63ea70f21cef8ba703db5170e56439a0756735a3c36cc62273f7a67357f08f87e80512971ab0013663bab9967a5947f6030fa549b5f1b20ceaf4a965481",
                    "0942409b1f4834e7a4658e99058bb888a8b5e21e36d11e0012c29b74749b1ae9e46f9472fdc9ec728d6945b4c82c0431125dec3976c517401a11857245f23de7",
                    CommonFunctions.getDateFromString(context, "2013-11-03", StaticVariables.dateFormat),
                    "1386097278.jpg"));
        }
    }

    public static void User_Init(Context context, SQLiteDatabase db) {
        if (!isTableExists(context, db, User_DBHandler.TABLE_NAME)) {
            User_DBHandler userDBHandler = new User_DBHandler(context);
            //userDBHandler.onUpgrade(db,1,1);
            userDBHandler.onCreate(db);
            userDBHandler.addItem(new User(1, "Anton", "Kraskov", CommonFunctions.getDateFromString(context, "2013-12-04", StaticVariables.dateFormat), "0", 90, 186, "Almaty", "kraskova@tcd.ie", "+77051944323", "123456aA", "123456aA", CommonFunctions.getDateFromString(context, "2013-11-03", StaticVariables.dateFormat), "1386097787s.jpg"));
            userDBHandler.addItem(new User(2, "Dina", "Kraskova", CommonFunctions.getDateFromString(context, "1986-08-04", StaticVariables.dateFormat), "1", 56, 170, "Almaty", "dina.kraskova@gmail.com", "", "123456aA", "123456aA", CommonFunctions.getDateFromString(context, "2013-12-03", StaticVariables.dateFormat), "138609733481816_Times_and_Dates.png"));
            userDBHandler.addItem(new User(3, "", "", CommonFunctions.getDateFromString(context, "0000-00-00", StaticVariables.dateFormat), "", 0, 0, "", "i6@bk.ru", "+38066836934", "123456aA", "123456aA", CommonFunctions.getDateFromString(context, "2013-12-04", StaticVariables.dateFormat), ""));
            userDBHandler.addItem(new User(4, "", "", CommonFunctions.getDateFromString(context, "0000-00-00", StaticVariables.dateFormat), "", 0, 0, "", "jvhadronick@gmail.com", "156", "123456aA", "123456aA", CommonFunctions.getDateFromString(context, "2013-12-04", StaticVariables.dateFormat), ""));
            userDBHandler.addItem(new User(5, "", "", CommonFunctions.getDateFromString(context, "0000-00-00", StaticVariables.dateFormat), "", 0, 0, "", "djaan@mai.ru", "87077575705", "123456aA", "123456aA", CommonFunctions.getDateFromString(context, "2014-01-18", StaticVariables.dateFormat), ""));
            userDBHandler.addItem(new User(6, "", "", CommonFunctions.getDateFromString(context, "0000-00-00", StaticVariables.dateFormat), "", 0, 0, "", "akraskov@lifebts.com", "8456424584", "123456aA", "123456aA", CommonFunctions.getDateFromString(context, "2014-01-22", StaticVariables.dateFormat), ""));
            userDBHandler.addItem(new User(7, "Andrey", "Sorokin", CommonFunctions.getDateFromString(context, "1990-04-22", StaticVariables.dateFormat), "0", 73, 168, "", "sorokin1990@gmail.com", "8456424584", "123456aA", "123456aA", CommonFunctions.getDateFromString(context, "2014-01-22", StaticVariables.dateFormat), ""));
        }
    }

    public static void MedicalLs_Init(Context context, SQLiteDatabase db) {
        if (!isTableExists(context, db, MedicalLs_DBHandler.TABLE_NAME)) {
            MedicalLs_DBHandler medicationDBHandler = new MedicalLs_DBHandler(context);
            //medicationDBHandler.onUpgrade(db,1,1);
            medicationDBHandler.onCreate(db);
            medicationDBHandler.addItem(new MedicalLs(context, 1, "РК-ЛС-5№000989", "ЛС", "Но-Шпа®", "Перерегистрация", "2008-09-22", 5, "2013-09-22", "Хиноин Завод фармацевтических и ", "ВЕНГРИЯ", "Лекарственный препарат", "Дротаверин", "A03AD02 Дротаверин", "40 мг", "5 год", "True", "True", "False", "True", "False", "True", "False", "СП РК", "42-4581-08"));
            medicationDBHandler.addItem(new MedicalLs(context, 2, "РК-ЛС-5№000987", "ЛС", "Но-Шпа®", "Перерегистрация", "2008-09-22", 5, "2013-09-22", "ХИНОИН завод Фармацевтических и ", "ВЕНГРИЯ", "Лекарственный препарат", "Дротаверин", "A03AD02 Дротаверин", "40 мг/2мл", "5 год", "True", "True", "True", "False", "False", "False", "False", "СП РК", "42-4582-08"));
            medicationDBHandler.addItem(new MedicalLs(context, 3, "РК-ЛС-5№008963", "ЛС", "Лимонидин", "Перерегистрация", "2008-09-22", 5, "2013-09-22", "Химфарм АО", "КАЗАХСТАН", "Лекарственная субстанция", "Нет данных", "0 Нет данных", "", "3 год", "False", "True", "False", "False", "False", "False", "False", "ФС РК", "42-1259-08"));
            medicationDBHandler.addItem(new MedicalLs(context, 4, "РК-ЛС-5№012525", "ЛС", "Вагинорм С®", "Регистрация", "2008-09-23", 5, "2013-09-23", "Артезан Фарма ГмбХ и Ко. КГ", "ГЕРМАНИЯ", "Лекарственный препарат", "Аскорбиновая кислота", "G01AD03 Аскорбиновая кислота", "250 мг", "3 год", "True", "True", "False", "False", "False", "False", "False", "СП РК", "42-4672-08"));
            medicationDBHandler.addItem(new MedicalLs(context, 5, "РК-ЛС-5№012530", "ЛС", "Витамин В комплекс", "Регистрация", "2008-09-23", 5, "2013-09-23", "СОФАРМА АО", "БОЛГАРИЯ", "Лекарственный препарат", "Нет данных", "A11EX Витамины группы B в комбин", "2 мл", "2 год", "True", "True", "True", "False", "False", "False", "False", "СП РК", "42-4668-08"));
            medicationDBHandler.addItem(new MedicalLs(context, 6, "РК-ЛС-5№012532", "ЛС", "Натрия хлорид", "Регистрация", "2008-09-23", 5, "2013-09-23", "Биосинтез ОАО", "РОССИЯ", "Лекарственный препарат", "Натрия хлорид", "B05CB01 Натрия хлорид", "0,9 %", "2 год", "False", "True", "True", "True", "False", "False", "False", "НД РК", "42-1564-08"));
            medicationDBHandler.addItem(new MedicalLs(context, 7, "РК-ЛС-5№012535", "ЛС", "Глюкоза", "Регистрация", "2008-09-23", 5, "2013-09-23", "Биосинтез ОАО", "РОССИЯ", "Лекарственный препарат", "Декстроза", "B05CX01 Декстроза", "10%", "2 год", "False", "True", "True", "True", "False", "False", "False", "НД РК", "42-1565-08"));
        }
    }

    public static void MyMedications_Init(Context context, SQLiteDatabase db) {
        if (!isTableExists(context, db, MyMedications_DBHandler.TABLE_NAME)) {
            MyMedications_DBHandler myMedicationsDBHandler = new MyMedications_DBHandler(context);
            //medicationDBHandler.onUpgrade(db,1,1);
            myMedicationsDBHandler.onCreate(db);
            Calendar c = Calendar.getInstance();
            myMedicationsDBHandler.addItem(new MyMedications(1, 7, 1, 1, new Date(c.getTimeInMillis() + 300000), 0.5, 1, 1, 1, 1, 1));
            myMedicationsDBHandler.addItem(new MyMedications(2, 7, 1, 1, new Date(c.getTimeInMillis() + 300000), 1, 1, 2, 1, 1, 1));
            myMedicationsDBHandler.addItem(new MyMedications(3, 7, 1, 3, new Date(c.getTimeInMillis() + 300000), 1.5, 1, 3, 1, 1, 1));
            myMedicationsDBHandler.addItem(new MyMedications(4, 7, 1, 3, new Date(c.getTimeInMillis() + 300000), 2, 1, 4, 1, 1, 1));
            myMedicationsDBHandler.addItem(new MyMedications(5, 7, 1, 5, new Date(c.getTimeInMillis() + 300000), 2.5, 1, 5, 1, 1, 1));
            myMedicationsDBHandler.addItem(new MyMedications(6, 7, 1, 5, new Date(c.getTimeInMillis() + 300000), 3, 1, 6, 1, 1, 1));
            myMedicationsDBHandler.addItem(new MyMedications(7, 7, 1, 5, new Date(c.getTimeInMillis() + 300000), 3.5, 1, 7, 1, 1, 1));
        }
    }

    public static boolean isTableExists(Context context, SQLiteDatabase db, String tableName) {
        try {
            Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.close();
                    return true;
                }
                cursor.close();
            }
            else {
                Log.e("SQLException", "Creating table " + tableName + " because it doesn't exist!" );
            }
            return false;
        } catch (Exception e) {
            Log.e("SQLException", "Exception in DBInit.isTableExists " + String.valueOf(e.getMessage()));
            e.printStackTrace();
            return false;
        }
    }
}
