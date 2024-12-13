<%-- 
    Document   : syarat_jualan_n9
    Created on : Jun 7, 2010, 1:17:24 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.lelong.PerintahJualanNegeri9ActionBean">

    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>
    <div align="left">
        <fieldset class="aras1">
            <legend>
                Syarat-syarat Jualan
            </legend><br>

            <p align="center"  style="font-size: 14pt">
                <b>SYARAT-SYARAT JUALAN</b><br>
            </p><br>

            <p align="justify" style="font-size: 11pt">
                1. Tertakluk kepada harga rizab yang ditetapkan, penawar yang tertinggi, jika dibenarkan oleh
                <b>Pentadbir Tanah Daerah ${actionBean.hakmilik.daerah.nama}</b>
                akan menjadi pembeli dan <b>Pentadbir Tanah Daerah ${actionBean.hakmilik.daerah.nama}</b>
                mempunyai kuasa untuk menolak sebarang tawaran. Jika tiada apa-apa
                pertikaian yang timbul mengenai penawar yang tertinggi, maka <b>Pentadbir Tanah Daerah ${actionBean.hakmilik.daerah.nama}</b>
                akan memutuskan pertikaian tersebut ataupun harta ini akan dilelong semula.<br><br>

                2. Pemegang Gadaian dengan ini adalah bebas untuk membuat tawaran di dalam lelongan ini.<br><br>

                3. Tawaran tidak boleh kurang daripada jumlah yang telah ditetapkan oleh <b>Pentadbir Tanah Daerah ${actionBean.hakmilik.daerah.nama}</b>
                pada masa lelongan dan tawaran tidak boleh ditarik balik.<br><br>

                4. Setiap penawar yang ingin membuat tawaran adalah dikehendaki mendepositkan dengan 
                <b>Pentadbir Tanah Daerah ${actionBean.hakmilik.daerah.nama} sebanyak 10% Dari Harga Rezab</b>
                yang tetap bagi harta tersebut melalui Bank Draf sahaja di bayar atas nama <b><c:forEach var="i" items="${actionBean.listPermohonanPihak}" ><c:if test='${i.jenis.kod == "PG"}'>${i.pihak.nama}&nbsp;</c:if></c:forEach></b>sebelum lelongan dimulakan.<br><br>

                5. Sesiapa yang ingin membuat tawaran dalam lelongan awam bagi pihak mana-mana individu lain, badan berkanun atau syarikat 
                dikehendaki mengemukakan surat lantikan kepada <b>Pentadbir Tanah Daerah ${actionBean.hakmilik.daerah.nama}</b>
                pada hari lelongan awam yang menyatakan bahawa beliau menandatangani
                semua dokumen berkaitan dengan syarat bagi Tanah Simpanan Melayu hanya orang Melayu Sahaja dibenarkan untuk bertindak sebagai
                ejen/wakil.<br><br>

                6. Sebaik sahaja selepas ketukan tukul terakhir, pembeli hendaklah membayar kepada
                <b><c:forEach var="i" items="${actionBean.listPermohonanPihak}" ><c:if test='${i.jenis.kod == "PG"}'>${i.pihak.nama}</c:if></c:forEach></b>
                melalui <b>Pentadbir Tanah Daerah ${actionBean.hakmilik.daerah.nama}</b>
                wang perbezaan antara <b>Deposit 10%</b>  harga beli menurut Syarat 4 di atas  dan jumlah bersamaan 10% dari harga beli sebenar
                dengan wang tunai sebagai wang bayaran pendahuluan (deposit) kepada harga pembelian itu dan hendaklah menandatangani Memorandum
                di bawah syarat-syarat ini. Jika pembeli gagal menjelaskan deposit tersebut atau enggan menendatangani Memorandum,wang deposit
                10% harga rezab yang dibayar menurut syarat 4 di atas akan dirampas oleh <b>Pentadbir Tanah Daerah ${actionBean.hakmilik.daerah.nama}</b>
                dan dibayar kepada Pemegang Gadaian setelah
                ditolak segala perbelanjaan jualan itu. Pemegang Gadaian mempunyai hak untuk menuntut daripada pembeli yang ingkar apa-apa kos
                tersebut kos jualan semula bersama dengan sama ada pengurangan harga (jika ada) akibat jualan semula atau tiada jualan semula
                mengikut mana yang berkenaan.<br><br>

                7. Jika Pembeli memberhentikan bayaran deposit 10% harga rizab maka harta itu akan ditarik balik daripada jualan itu dan Pemegang
                Gadai mempunyai hak untuk menuntut daripada Pembeli yang ingkar apa-apa kos termasuk kos jualan semula bersama dengan samada
                pengurangan harga (jika ada) akibat jualan semula atau tiada jualan semula mengikut mana yang berkenaan.<br><br>

                8. Baki harga pembelian hendaklah dibayar oleh pembeli dalam tempoh <b>120(Satu Ratus Dua Puluh Hari)</b> dari tarikh jualan
                kepada Pemegang Gadaian melalui Draf dibayar atas nama <b><c:forEach var="i" items="${actionBean.listPermohonanPihak}" ><c:if test='${i.jenis.kod == "PG"}'>${i.pihak.nama}</c:if></c:forEach></b>.
                Jika baki harga pembelian itu tidak dibayar dalam tempoh
                yang dibenarkan,harta ini akan dilelong sekali lagi. <b>Deposit 10%</b> daripada harga jualan ini hendaklah dirampas dan menjadi
                hak Pemegang Gadaian. Pembeli yang engkar tidak berhak mambuat tuntutan ke atas wang hasil jualan lelong harta yang sama
                kemudiannya. Tiada lanjutan tempoh bayaran tersebut.<br><br>

                9. Mulai daripada masa harta tersebut dijual, ianya menjadi risiko tunggal. Pembeli jika berlakunya kerugian atau kerosakkan yang
                dialami kerana kebakaran atau kemalangan atau dengan cara-cara lain.<br><br>

                10. Harta ini adalah dipercayai dan hendaklah dianggap sebagai diperihalkan dengan betul dan dijadual tertakluk kepada semua
                ismen, kaveat,tenansi, tnggungan dan hak (jika ada) yang wujud di atas atau terhadapnya tanpa apa-apa tanggungan yang timbul
                untuk mentakrifkannya dan tiada penyataan khilaf atau perihal khilaf boleh membatalkan jualan ini dan tiada bayaran rugi
                dibenarkan mengenainya.<br><br>

                11. Apa-apa bayaran cukai tanah,bayaran taksiran setakat tarikh lelongan dan semua perbelanjaan yang kena dibayar bagi membuat
                atau melaksanakan perintah jualan ini hendaklah dibayar daripada harga pembelian. Segala bayaran lain berhubung dengan
                pendaftaran perakuan jualan hendaklah ditanggung oleh Pembeli.<br><br>

                12. Pemegang Gadaian tidak akan bertanggungjawab untuk menyerahkan milikan kosong harta berkenaan kepada Pembeli.<br><br>

                13. Bagi maksud syarat-syarat ini,tempoh yang diberikan untuk pembayaran baki harga pembelian <b>(iaitu 120 Hari)</b>
                hendaklah dianggap sebagai penting dalam kontrek ini.<br><br>

                14. Jika berlaku apa-apa percanggahan dalam syarat-syarat ini dengan peruntukan dalam Bahagian Enam Belas - Bab 3 -
                Remedi Pemegang Gadaian: Jualan, Kanun Tanah Negara, peruntukan-peruntukan itu hendaklah mengatasi syarat-syarat ini.<br><br>

                <b>
                    15. Bagi Tanah Simpanan Melayu Hanya Ornag Melayu Sahaja Dibenarkan Untuk Bertindak Sebagai Ejen Atau Wakil.
                </b>
       
            </p>


            <br>
        </fieldset>

    </div>
</s:form>