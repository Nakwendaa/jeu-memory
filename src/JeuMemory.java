import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class JeuMemory {

	
	/**
	 * 
	 * La méthode static procède à une vérification de la validité des arguments entrés en paramètre sous forme de tableau.
	 * 
	 * @param args
	 * 			Tableau de String représentant les arguments entrés en paramètre par l'utilisateur
	 * @return
	 */
	
	public static boolean verifieArguments(String[] args) { //Vérifie que les arguments sont bien des nombres
		if (args.length != 5) {
			return false;
		}
		for (int i = 0; i < args.length; i++) {
			for (int j = 0; j < args[i].length(); j++) {
				if (((int)args[i].charAt(j) < 48 || (int)args[i].charAt(j) > 57)) {
					return false;
				}
			} 
			if (i == 0) {
				if (Integer.parseInt(args[i]) == 0) {
					return false;
				}
				if (Integer.parseInt(args[i]) == 1) {
					if (Integer.parseInt(args[1]) == 0 || Integer.parseInt(args[1]) == 1) {
						return false;
					}
					if ( Integer.parseInt(args[1]) % 2 != 0) {
						return false;
					}
				}
			}
			if (i == 1) {
				if (Integer.parseInt(args[i]) == 0) {
					return false;
				}
				if (Integer.parseInt(args[i]) == 1) {
					if (Integer.parseInt(args[0]) == 0 || Integer.parseInt(args[0]) == 1) {
						return false;
					}
					if ( Integer.parseInt(args[0]) % 2 != 0) {
						return false;
					}
				}
			}
		}
		if ((int)args[args.length - 1].charAt(args[args.length - 1].length() - 1) < 48 || args[args.length - 1].length() > 1 || (int)args[args.length - 1].charAt(args[args.length - 1].length() - 1) > 53) {
			return false;
		}
		return true;
	}
	
	//Ces 4 variables sont utilisées pour calculer la taille des images pour qu'elle soit adapté
	//à l'écran de l'utilisateur.
	public static int nRangees;
	public static int nColonnes;
	public static int width; //Largeur de l'ecran de l'utilisateur.
	public static int height; //Hauteur de l'écran de l'utilisateur.
	public static int numeroDeTheme = 0;
	
	// Bases de données des thèmes.
	
	public static String[] tabURL1 = {"http://img.lum.dolimg.com/v1/images/databank_admiralmotti_01_169_f01d2570.jpeg?region=417%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/e5d_ia_1189_6c16bed1.jpeg?region=144%2C0%2C808%2C809&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_admiralpiett_01_169_18014135.jpeg?region=423%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/Anakin-Skywalker_d3330724.jpeg?region=188%2C0%2C675%2C675&width=480",
		  "http://img.lum.dolimg.com/v1/images/Appo-main-image_653bd25a.jpeg?region=173%2C0%2C948%2C949&width=480",
		  "http://img.lum.dolimg.com/v1/images/Bail_Organa_e28bc2fc.jpeg?region=555%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/barada_cd0c628e.jpeg?region=398%2C0%2C666%2C664&width=480",
		  "http://img.lum.dolimg.com/v1/images/ep7_ia_162323_j_077412a0.jpeg?region=596%2C0%2C655%2C654&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_benquadinaros_01_169_0c77b6a0.jpeg?region=372%2C0%2C876%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/bg-81_283b6580.jpeg?region=528%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_berulars_01_169_68101518.jpeg?region=76%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_bibfortuna_01_169_01aef5b7.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/image_606ff7f7.jpeg?region=520%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_bosslyonie_01_169_3310c7e1.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_bossnass_01_169_ef11c0db.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/Boba-Fett_61fdadfd.jpeg?region=250%2C0%2C675%2C675&width=480",
		  "http://img.lum.dolimg.com/v1/images/boshek-main-image_f7900f5e.jpeg?region=639%2C0%2C661%2C661&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_bossk_01_169_c3c42fbe.jpeg?region=683%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/Bultar-swan_7e78a9bc.jpeg?region=216%2C120%2C744%2C744&width=480",
		  "http://img.lum.dolimg.com/v1/images/bulduga_53057af7.jpeg?region=347%2C0%2C803%2C803&width=480",
		  "http://img.lum.dolimg.com/v1/images/C-3PO-See-Threepio_68fe125c.jpeg?region=353%2C0%2C791%2C793&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_captainantilles_01_169_75e05265.jpeg?region=0%2C0%2C876%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/captain-merumeru_87bf7c7d.jpeg?region=448%2C0%2C664%2C664&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_captainpanaka_01_169_6a3ccac5.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/captain-typho_f9e8d7bd.jpeg?region=246%2C0%2C667%2C668&width=480",
		  "http://img.lum.dolimg.com/v1/images/chewie-db_2c0efea2.jpeg?region=376%2C151%2C799%2C799&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_cliegglars_01_169_c2f0b9cb.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_clonecommanderbacara_01_169_aa642060.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_clonecommandercody_01_169_f3aa1a91.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/e5d_ia_1559_acf49910.jpeg?region=943%2C0%2C815%2C815&width=480",
		  "http://img.lum.dolimg.com/v1/images/Darth-Maul_632eb5af.jpeg?region=433%2C47%2C853%2C853&width=480",
		  "http://img.lum.dolimg.com/v1/images/Darth-Vader_6bda9114.jpeg?region=236%2C0%2C928%2C928&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_daultaydofine_01_169_c94b9f38.jpeg?region=301%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/image_220cf6a1.jpeg?region=673%2C0%2C950%2C950&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_dexterjettster_01_169_09c89b71.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/dex-tiree_3efe56ad.jpeg?region=449%2C0%2C663%2C663&width=480",
		  "http://img.lum.dolimg.com/v1/images/Eeth-Koth2_246ea172.jpeg?region=456%2C0%2C568%2C568&width=480",
		  "http://img.lum.dolimg.com/v1/images/Emperor-Palpatine_7ac4a10e.jpeg?region=311%2C0%2C900%2C900&width=480",
		  "http://img.lum.dolimg.com/v1/images/x551_tea0010_denoised3_16int_8a29e084.jpeg?region=469%2C0%2C655%2C654&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_generalmadine_01_169_81edb078.jpeg?region=477%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/ep7_ia_22591_r_8396f2d2.jpeg?region=0%2C193%2C1040%2C1040&width=480",
		  "http://img.lum.dolimg.com/v1/images/General-Grievous_c9df9cb5.jpeg?region=331%2C0%2C675%2C675&width=480",
		  "http://img.lum.dolimg.com/v1/images/Han-Solo_1d08eb2e.jpeg?region=573%2C0%2C899%2C901&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_greedo_01_169_3e4b96ef.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/hermione-bagwa-main-image_edc65dfa.jpeg?region=176%2C0%2C1019%2C1019&width=480",
		  "http://img.lum.dolimg.com/v1/images/Jabba-The-Hutt_b5a08a70.jpeg?region=263%2C0%2C675%2C675&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_jangofett_01_169_884cefab.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_jarjarbinks_01_169_c70767ab.jpeg?region=230%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/jocasta-nu_a3b32f08.jpeg?region=501%2C0%2C1242%2C1242&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_kiadimundi_01_169_0a8842d3.jpeg?region=301%2C0%2C876%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_kitfisto_01_169_21517d01.jpeg?region=436%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/kaminoan-main-image_40db5ba5.jpeg?region=246%2C0%2C667%2C665&width=480",
		  "http://img.lum.dolimg.com/v1/images/Lando-Calrissian_a679fe1e.jpeg?region=256%2C0%2C900%2C899&width=480",
		  "http://img.lum.dolimg.com/v1/images/logray-main-image_05b9e023.jpeg?region=57%2C0%2C1447%2C1447&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_lottdod_01_169_7219a66c.jpeg?region=3%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/Luke-Skywalker_dd9c9f9b.jpeg?region=389%2C0%2C864%2C864&width=480",
		  "http://img.lum.dolimg.com/v1/images/luminara_c05b2971.jpeg?region=409%2C0%2C900%2C901&width=480",
		  "http://img.lum.dolimg.com/v1/images/Mace-Windu_b35242e5.jpeg?region=375%2C0%2C922%2C921&width=480",
		  "http://img.lum.dolimg.com/v1/images/mas-amedda-main-image_756fa0ae.jpeg?region=43%2C68%2C746%2C746&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_niennunb_01_169_31eccf40.jpeg?region=228%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_nutegunray_01_169_9d66ded2.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/Obi-Wan-Kenobi_6d775533.jpeg?region=149%2C0%2C864%2C864&width=480",
		  "http://img.lum.dolimg.com/v1/images/Padme-Amidala_05d50c8a.jpeg?region=336%2C0%2C864%2C864&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_owenlars_01_169_effce0f8.jpeg?region=230%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/passel-argente-main-image_f5e71c22.jpeg?region=0%2C322%2C1560%2C1553&width=480",
		  "http://img.lum.dolimg.com/v1/images/Princess-Leia-Organa_d7761ff5.jpeg?region=608%2C0%2C900%2C900&width=480",
		  "http://img.lum.dolimg.com/v1/images/Qui-Gon-Jinn_d89416e8.jpeg?region=353%2C0%2C862%2C865&width=480",
		  "http://img.lum.dolimg.com/v1/images/queen-jamillia_0cb29995.jpeg?region=490%2C0%2C878%2C877&width=480",
		  "http://img.lum.dolimg.com/v1/images/R2-D2_41dacaa9.jpeg?region=549%2C0%2C864%2C864&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_sabe_01_169_45c104cd.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_shmiskywalkerlars_01_169_7449f0a8.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_siobibble_01_169_68afc1e9.jpeg?region=127%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_monmothma_01_169_b62808e8.jpeg?region=563%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/valorum_a31904f4.jpeg?region=572%2C0%2C955%2C955&width=480",
		  "http://img.lum.dolimg.com/v1/images/teebo_48370833.jpeg?region=0%2C268%2C1560%2C1564&width=480",
		  "http://img.lum.dolimg.com/v1/images/Yoda-Retina_2a7ecc26.jpeg?region=461%2C0%2C864%2C864&width=480"}; //Personnages

	public static String[] tabURL2 = {"http://img.lum.dolimg.com/v1/images/screen_shot_2015-05-26_at_5_16a39e17.png?region=107%2C0%2C598%2C598&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_aatbattletank_01_169_9de46aea.jpeg?region=260%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_alderaancruiser_01_169_c60ce268.jpeg?region=255%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_anakinskywalkerspodracer_01_169_fe359d32.jpeg?region=553%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_arc170starfighter_01_169_f932abcb.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/AT-AT_89d0105f.jpeg?region=263%2C0%2C780%2C781&width=480",
		  "http://img.lum.dolimg.com/v1/images/AT-DP_11f721ba.jpeg?region=537%2C0%2C1080%2C1080&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_atrtwalker_01_169_dc4f22ad.jpeg?region=445%2C0%2C876%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/e6d_ia_5724_a150e6d4.jpeg?region=548%2C0%2C801%2C802&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_bwingfighter_01_169_460cc528.jpeg?region=450%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/prototype-b-wing_4a86a3b4.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/vaders-tie-fighter_8bcb92e1.jpeg?region=185%2C0%2C1169%2C1172&width=480",
		  "http://img.lum.dolimg.com/v1/images/image_b15d04ff.jpeg?region=387%2C0%2C787%2C787&width=480",
		  "http://img.lum.dolimg.com/v1/images/coronet-main-image_405355e7.jpeg?region=731%2C0%2C677%2C675&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_coruscantairtaxi_01_169_a36dcf1f.jpeg?region=322%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/1-1-starfighter_decf3188.jpeg?region=0%2C0%2C736%2C736&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_flashspeeder_01_169_48978def.jpeg?region=81%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/ep3_ia_96565_46396938.jpeg?region=470%2C0%2C820%2C818&width=480",
		  "http://img.lum.dolimg.com/v1/images/medium-transport-1-1_db17a48f.jpeg?region=0%2C0%2C736%2C736&width=480",
		  "http://img.lum.dolimg.com/v1/images/Bad-Batch-Transport_37c38913.jpeg?region=436%2C0%2C780%2C780&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_gauntletfighter_01_169_0f9c1f48.jpeg?region=601%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/x1626imax_tea0050_pub_imax_nomb_16int_0c7c75cc.jpeg?region=0%2C108%2C606%2C606&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_gunganbongosubmarine_01_169_fc9286be.jpeg?region=393%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/Imperial-Light-Cruiser-Square_10fb1fd8.jpeg?region=0%2C0%2C736%2C736&width=480",
		  "http://img.lum.dolimg.com/v1/images/imperial-interdictor_f797f8ad.jpeg?region=382%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/image_c5bf105d.jpeg?region=420%2C0%2C1080%2C1080&width=480",
		  "http://img.lum.dolimg.com/v1/images/Star-Destroyer_ab6b94bb.jpeg?region=356%2C0%2C900%2C900&width=480",
		  "http://img.lum.dolimg.com/v1/images/veh_ia_1752_040381b2.jpeg?region=100%2C0%2C983%2C981&width=480",
		  "http://img.lum.dolimg.com/v1/images/image_3aaf40b1.jpeg?region=420%2C0%2C1080%2C1080&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_jedit6shuttle_01_169_2fd46362.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/Millennium-Falcon_018ea796.jpeg?region=336%2C0%2C865%2C865&width=480",
		  "http://img.lum.dolimg.com/v1/images/image_070e5a4a.jpeg?region=22%2C0%2C1012%2C1013&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_naboon1starfighter_01_169_26691adf.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_nabooroyalstarship_01_169_e61f677e.jpeg?region=360%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/neimoidian_escort_shuttle_7602d574.jpeg?region=308%2C0%2C818%2C818&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_podracer_01_169_89a8621d.jpeg?region=542%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_republicattackcruiser_01_169_812f153d.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_republiccruiser_01_169_fd769e33.jpeg?region=504%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_sandcrawler_01_169_55acf6cb.jpeg?region=431%2C0%2C877%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/sebulba-1-1_a92f1148.jpeg?region=0%2C0%2C736%2C736&width=480",
		  "http://img.lum.dolimg.com/v1/images/snowspeeder_ef2f9334.jpeg?region=379%2C0%2C1366%2C1365&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_sithspeeder_01_169_cfa01a05.jpeg?region=563%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_speederbike_01_169_c4204c29.jpeg?region=192%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_sithinfiltrator_01_169_1bd0a638.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_stap_01_169_75029522.jpeg?region=287%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/image_559a9c59.jpeg?region=463%2C0%2C1080%2C1080&width=480",
		  "http://img.lum.dolimg.com/v1/images/ep5_key_504_6c3982bb.jpeg?region=214%2C0%2C853%2C853&width=480",
		  "http://img.lum.dolimg.com/v1/images/tie-interceptor-2_b2250e79.jpeg?region=366%2C0%2C1040%2C1040&width=480",
		  "http://img.lum.dolimg.com/v1/images/TIE-Fighter_25397c64.jpeg?region=448%2C0%2C1153%2C1153&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_tradefederationlandingship_01_169_00567a01.jpeg?region=458%2C209%2C669%2C669&width=480",
		  "http://img.lum.dolimg.com/v1/images/databank_tradefederationbattleship_01_169_fc5458ce.jpeg?region=341%2C0%2C878%2C878&width=480",
		  "http://img.lum.dolimg.com/v1/images/Y-Wing-Fighter_0e78c9ae.jpeg?region=192%2C0%2C864%2C864&width=480",
		  "http://img.lum.dolimg.com/v1/images/landspeeder-1-1_68b36956.jpeg?region=0%2C0%2C736%2C736&width=480",
		  "http://img.lum.dolimg.com/v1/images/X-Wing-Fighter_47c7c342.jpeg?region=272%2C0%2C863%2C865&width=480",
		  "http://img.lum.dolimg.com/v1/images/image_2780d5ff.jpeg?region=245%2C0%2C1063%2C1060&width=480",
		  "http://img.lum.dolimg.com/v1/images/zygerrian_slave_ship_e5edce00.jpeg?region=897%2C0%2C801%2C799&width=480"}; //Vaisseaux véhicules


//Personnages: 90 environ

	public static String[] tabMots1 = {"Dark Vador", "Luke Skywalker", "Princesse Leia", "Anakin Skywalker", "Boba Fett", "Han Solo", 
		   "Obi-Wan Kenobi", "C3-PO", "RD-D2", "Chewbacca", "Owen Lars", "Beru Lars", "Lando Calrissian", 
		   "Yoda", "Empereur Palpatine", "Mace Windu", "Qui-Gon Jinn", "Reine Amidala", "Jar Jar Binks", 
		   "Boss Nass", "Capitaine Panaka", "Chancelier Valorum", "Dark Maul", "Sio Bibble", "Shmi Skywalker", 
		   "Comte Dooku", "Zam Wesell", "Poggle le Bref", "Jango Fett", "Taun We", "Dexter Jettster", "Capitaine Typho", 
		   "Général Grievous", "Tarfful", "Bail Organa", "Dark Bane", "Barada", "Jabba le Hutt", "Ask Aak", "Amiral Ackbar",
		   "Mas Amedda", "Wedge Antilles", "Passel Argente", "Hermione Bagwa", "Kirster Banai", "Walex Blissex", 
		   "Joruus C'Baoth", "Yarna D'Al'Gargan", "Figrin D'An", "Biggs Darklighter", "Soontir Fel" , "Silver Fyre", "Nute Gunray",
		   "Prince Isolder", "Reine Jamillia", "Talon Karrde", "Ki-Adi-Mundi", "Plo Koon", "Eeth Koth", "Tsavong Lah", "Lobot ",
		   "Logray", "Crix Madine", "Ephant Mon", "Jocasta Nu", "Ric Olié", "Firmus Piett", "Sate Pestage", "Yarael Poof",
		   "Dash Rendar", "Horox Ryyder", "Viqi Shesh", "Aurra Sing", "Anakin Solo", "Tionne Solusar", "Bultar Swan",
		   "Capitaine Tarpals", "Grand Moff Tarkin", "Rep Teers", "Bria Tharen", "Shaak Ti", "Tikkes", "Coleman Trebor",
		   "Luminara Unduli", "Général Veers", "Taun We", "Prince Xizor", "Yaddle", "Zsinj", "Bogg Tyrell", "Depa Billaba"};

	public static String[] tabMots2 = {"Abregado-rae", "Adumar", "Agamar", "Alderaan", "Almania", "Ambria", "Atzerri", "Bakura", "Bandomeer", 
		   "Bastion", "Bespin", "Brodo Asogi", "Bonadan", "Borleias", "Bothawui", "Brentaal 4", "Byss", "Carida",
		   "Cato Neimoidia", "Chandrila", "Corellia", "Corulag", "Coruscant", "Cymoon 1", "Dac", "Dagobah", "Dantooine",
		   "Dathomir", "Devaron", "Duro", "Endor", "Eriadu", "Etti 4", "Falleen", "Fondor", "Felucia", "Géonosis",
		   "Glee Anselm", "Gyndine", "Hapes", "Honoghr", "Hoth", "Ilum", "Ithor", "Jabiim", "Jakku", "Kalarba",
		   "Kamino", "Kashyyyk", "Kiffex", "Kiffy", "Klatooine", "Korriban", "Kothlis", "Kuat", "Kubindi", "Lothal",
		   "Malastare", "Mandalore", "Mustafar", "Muunilist", "Mygeeto", "Myrkr", "Naboo", "Nal Hutta", "Nar Shaddaa",
		   "Obroa-skai", "Onderon", "Ord Mantell", "Ossus", "Polis Massa", "Ralltiir", "Raxus", "Rodia", "Ruusan", "Ryloth",
		   "Saleucami", "Selonia", "Sernpidal", "Sullust", "Sulon", "Talus", "Taris", "Tatooine", "Telos 4", "Tralus",
		   "Trandosha", "Tython", "Umbara", "Utapau", "Vanqor", "Vjun", "Vortex", "Wayland", "Yaga Minor", "Yavin",
		   "Yavin 4", "Yinchorr", "Ylesia", "Ziost", "Zonama Sekot", "Zygerria"};

// Fonction principale
	
	public static void main(String[] args) {
		if (!verifieArguments(args)) {
			System.out.println("Utilisation: java JeuMemory nRangees nColonnes delaiAffichageInitial(ms) delaiAffichageMauvaisePaire(ms) numeroDeTheme");
			System.out.println("Ex: java JeuMemory 5 6 5000 1000 3\n");
			System.out.println("Voici la liste des themes disponibles");
			System.out.println("0: Couleurs");
			System.out.println("1: Noms de personnages Star Wars");
			System.out.println("2: Noms de planètes Star Wars");
			System.out.println("3: Images de personnages Star Wars");
			System.out.println("4: Images de vaisseaux et véhicules Star Wars");
			System.out.println("5: Mélange des thèmes 0 à 4");
			System.out.print("Veuillez entrez vos nouveaux arguments: ");
			Scanner scan = new Scanner(System.in);
			String entree = scan.nextLine();
			String[] arguments = entree.split("\\s+");
			System.out.println("");
			main(arguments);
		}
		else {
			nRangees = Integer.parseInt(args[0]);
			nColonnes = Integer.parseInt(args[1]);
			numeroDeTheme = Integer.parseInt(args[4]);
			int n = nRangees*nColonnes/2;
			JFrame fenetre = new JFrame("Memory par Paul Chaffanet et Samuel Guigui");
			fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
			Toolkit t = fenetre.getToolkit(); 
			Dimension d = t.getScreenSize();
			width = d.width; 
			height = d.height;
			if (nRangees == 1) {
				n = nColonnes/2;
			}
			else if (nColonnes == 1) {
				n = nRangees/2;
			}
			int delaiAffichageInitial = Integer.parseInt(args[2]);
			int delaiAffichageMauvaisePaire = Integer.parseInt(args[3]);
			int numeroDeTheme = Integer.parseInt(args[4]);
			Carte[] cartes = null;	 
			GenerateurDeCartes[] generateurs = {new GenerateurDeCartesCouleur("Couleur"), 
								 new GenerateurDeCartesMot("Mot1", tabMots1), 
								 new GenerateurDeCartesMot("Mot2", tabMots2), 
								 new GenerateurDeCartesImage("Image1", tabURL1),
								 new GenerateurDeCartesImage("Image2", tabURL2),
								 null};
	 		GenerateurDeCartes[] generateurMultiple = {generateurs[0],generateurs[1],generateurs[2],generateurs[3],generateurs[4]};
	 		generateurs[5] = new GenerateurDeCartesMultiple("Multiple", generateurMultiple);
			cartes = generateurs[numeroDeTheme].generePairesDeCartesMelangees(n);		
			PanneauDeCartes panneau = new PanneauDeCartes(nRangees, nColonnes, cartes, delaiAffichageInitial, delaiAffichageMauvaisePaire);
			FlowLayout flowLayout = new FlowLayout();
			JPanel panel = new JPanel();
			panel.setLayout(flowLayout);
			panel.add(panneau);
			panneau.setPreferredSize(panneau.getPreferredSize());
			fenetre.setContentPane(panel);
			fenetre.setVisible(true);
			fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
			fenetre.pack();
			fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}

			