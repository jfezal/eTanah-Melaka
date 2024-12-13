<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function popupMukim(){
        window.open("${pageContext.request.contextPath}/consent/laporan/mengikutMukim", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    function popupTanah(){
        window.open("${pageContext.request.contextPath}/consent/laporan/kategoriTanah", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    function popupJenis(){
        window.open("${pageContext.request.contextPath}/consent/laporan/mengikutJenis", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    function popupFail(){
        window.open("${pageContext.request.contextPath}/consent/laporan/pergerakanFail", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    function popupKemajuan(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporanKemajuan", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    function popupDaerah(){
        window.open("${pageContext.request.contextPath}/consent/laporan/mengikutDaerah", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    function popupKelulusan(){
        window.open("${pageContext.request.contextPath}/consent/laporan/jenisKelulusan", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    function popupBangsa(){
        window.open("${pageContext.request.contextPath}/consent/laporan/jenisBangsa", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    function popupTempohKelulusan(){
        window.open("${pageContext.request.contextPath}/consent/laporan/tempohKelulusan", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    function popupJumlahPermohonan(){
        window.open("${pageContext.request.contextPath}/consent/laporan/jumlahPermohonan", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    function popupKelulusanIkutTanah(){
        window.open("${pageContext.request.contextPath}/consent/laporan/kelulusanIkutTanah", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    function popupBangsaDetail(){
        window.open("${pageContext.request.contextPath}/consent/laporan/bangsaDetail", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    function popupMohonPindahMilik(){
        window.open("${pageContext.request.contextPath}/consent/laporan/mohonPindahMilik", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

    function popupPindahDanGadaian(){
        window.open("${pageContext.request.contextPath}/consent/laporan/pindahDanGadaian", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

    function popupLaporan15(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporan15", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

    function popupLaporan16(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporan16", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

    function popupLaporanAdat1(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporanAdat1", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

    function popupLaporanAdat2(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporanAdat2", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

    function popupLaporanAdat3(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporanAdat3", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

    function popupLaporanAdat4(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporanAdat4", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

    function popupLaporanAdat5(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporanAdat5", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

    function popupLaporanAdat6(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporanAdat6", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

    function popupLaporanAdat7(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporanAdat7", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

    function popupLaporanAdat8(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporanAdat8", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

    function popupLaporanAdat9(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporanAdat9", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

    function popupLaporanAdat10(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporanAdat10", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

    function popupLaporanAdat11(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporanAdat11", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupLaporanAdat12(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporanAdat12", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupPrestasiPindahMilik(){
        window.open("${pageContext.request.contextPath}/consent/laporan/laporanPrestasiPindahMilik", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
        function popupUnitMMK(){
        window.open("${pageContext.request.contextPath}/consent/laporan/unitMMK", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    function popupPindahMilik(){
        window.open("${pageContext.request.contextPath}/consent/laporan/pindahMilikWNA", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupPenerimaPindhMlk(){
        window.open("${pageContext.request.contextPath}/consent/laporan/penerimaPindahMilikWNA", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupPrestasi(){
        window.open("${pageContext.request.contextPath}/consent/laporan/prestasiUrusan", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupPrestasiCM(){
        window.open("${pageContext.request.contextPath}/consent/laporan/prestasiUrusanCM", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupPemohon(){
        window.open("${pageContext.request.contextPath}/consent/laporan/statistikPemohon", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupPenerima(){
        window.open("${pageContext.request.contextPath}/consent/laporan/statistikPenerima", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupMaklumatHakmilik(){
        window.open("${pageContext.request.contextPath}/consent/laporan/statistikMaklumatHakmilik", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupRingkasanKelulusanPTG(){
        window.open("${pageContext.request.contextPath}/consent/laporan/ringkasanKelulusanPTG", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupRingkasanKelulusanKM_PTG(){
        window.open("${pageContext.request.contextPath}/consent/laporan/ringkasanKelulusanKM_PTG", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupRingkasanKelulusanPTD(){
        window.open("${pageContext.request.contextPath}/consent/laporan/ringkasanKelulusanPTD", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupStatistikTempohKelulusanPTG(){
        window.open("${pageContext.request.contextPath}/consent/laporan/statistikTempohKelulusanPTG", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupStatistikTempohKelulusanCM(){
        window.open("${pageContext.request.contextPath}/consent/laporan/statistikTempohKelulusanCM", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupRingkasanKelulusanKM(){
        window.open("${pageContext.request.contextPath}/consent/laporan/ringkasanKelulusanKM", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }
    
    function popupStatistikTempohKelulusanPTD(){
        window.open("${pageContext.request.contextPath}/consent/laporan/statistikTempohKelulusanPTD", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,resizable=1,width=1000,height=600");
    }

</script>

<s:form beanclass="etanah.view.stripes.consent.LaporanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Laporan</legend><br><font color="black">
                &nbsp;&nbsp; 1) &nbsp;&nbsp;<a href="#" onclick="popupMukim();return false;">Statistik Hakmilik Mengikut Mukim</a><br>
                &nbsp;&nbsp; 2) &nbsp;&nbsp;<a href="#" onclick="popupTanah();return false;">Statistik Permohonan Mengikut Kategori Tanah</a><br>
                &nbsp;&nbsp; 3) &nbsp;&nbsp;<a href="#" onclick="popupJenis();return false;">Statistik Keputusan Permohonan Mengikut Jenis Permohonan Dan Pejabat</a><br>
                <%--&nbsp;&nbsp; 4) &nbsp;&nbsp;<a href="#" onclick="popupFail();return false;">Statistik Permohonan Dan Pergerakan Fail Permohonan Mengikut Pejabat</a><br>--%>
                &nbsp;&nbsp; 4) &nbsp;&nbsp;<a href="#" onclick="popupBangsa();return false;">Statistik Permohonan Mengikut Bangsa Pemohon</a><br>
                &nbsp;&nbsp; 5) &nbsp;&nbsp;<a href="#" onclick="popupKemajuan();return false;">Laporan Kemajuan Permohonan Kebenaran Urusniaga</a><br>
                &nbsp;&nbsp; 6) &nbsp;&nbsp;<a href="#" onclick="popupDaerah();return false;">Graf Permohonan Mengikut Pejabat</a><br>
                &nbsp;&nbsp; 7) &nbsp;&nbsp;<a href="#" onclick="popupKelulusan();return false;">Graf Permohonan Mengikut Jenis Kelulusan</a><br>
                <c:if test="${actionBean.kodNegeri eq '04'}">
                    &nbsp;&nbsp; 8) &nbsp;&nbsp;<a href="#" onclick="popupPrestasiPindahMilik();return false;">Laporan Prestasi Permohonan Kebenaran Pindahmilik </a><br>
                    &nbsp;&nbsp; 9) &nbsp;&nbsp;<a href="#" onclick="popupUnitMMK();return false;">Senarai Permohonan Daripada Warganegara Asing Untuk Memiliki Hartanah Bagi Projek (Unit MMK)</a><br>
                    &nbsp; 10) &nbsp;&nbsp;<a href="#" onclick="popupPindahMilik();return false;">Senarai Permohonan Pindahmilik Warganegara Asing</a><br>
                    &nbsp; 11) &nbsp;&nbsp;<a href="#" onclick="popupPenerimaPindhMlk();return false;">Senarai Permohonan Penerima Pindahmilik Warganegara Asing</a><br>
                    &nbsp; 12) &nbsp;&nbsp;<a href="#" onclick="popupPrestasi();return false;">Laporan Pemantauan Prestasi Urusan Kebenaran Pindahmilik/Cagaran Kelulusan Pengarah Tanah Dan Galian (PTG)</a><br>
                    &nbsp; 13) &nbsp;&nbsp;<a href="#" onclick="popupPrestasiCM();return false;">Laporan Pemantauan Prestasi Urusan Kebenaran Pindahmilik/Cagaran/Pajakan/Sewaan Kelulusan Ketua Menteri</a><br>
                    &nbsp; 14) &nbsp;&nbsp;<a href="#" onclick="popupPemohon();return false;">Laporan Statistik Maklumat Urusan Kebenaran Pindahmilik/Pajakan/Cagaran/Sewaan (Kategori Pemohon)</a><br>
                    &nbsp; 15) &nbsp;&nbsp;<a href="#" onclick="popupPenerima();return false;">Laporan Statistik Maklumat Urusan Kebenaran Pindahmilik/Pajakan/Cagaran/Sewaan (Kategori Penerima)</a><br>
                    &nbsp; 16) &nbsp;&nbsp;<a href="#" onclick="popupMaklumatHakmilik();return false;">Laporan Statistik Maklumat Kebenaran Urusan Pindahmilik/Pajakan/Cagaran/Sewaan (Maklumat Hakmilik)</a><br>
                    &nbsp; 17) &nbsp;&nbsp;<a href="#" onclick="popupStatistikTempohKelulusanPTG();return false;">Statistik Permohonan Kebenaran Pindahmilik Kelulusan Pengarah Tanah Dan Galian (PTG) (Mengikut Tempoh Masa)</a><br>
                    &nbsp; 18) &nbsp;&nbsp;<a href="#" onclick="popupStatistikTempohKelulusanCM();return false;">Statistik Permohonan Kebenaran Pindahmilik Kelulusan Ketua Menteri (Mengikut Tempoh Masa)</a><br>
                    &nbsp; 19) &nbsp;&nbsp;<a href="#" onclick="popupStatistikTempohKelulusanPTD();return false;">Statistik Permohonan Kebenaran Pindahmilik Kelulusan Pentadbir Tanah Daerah (PTD) (Mengikut Tempoh Masa)</a><br>
                </c:if>
                <c:if test="${actionBean.kodNegeri eq '05'}">
                    &nbsp;&nbsp; 8) &nbsp;&nbsp;<a href="#" onclick="popupTempohKelulusan();return false;">Statistik Keputusan Mengikut Tempoh Permohonan</a><br>
                    &nbsp;&nbsp; 9) &nbsp;&nbsp;<a href="#" onclick="popupJumlahPermohonan();return false;">Statistik Jumlah Permohonan</a><br>
                    &nbsp;&nbsp; 10) <a href="#" onclick="popupKelulusanIkutTanah();return false;">Statistik Kelulusan Mengikut Kategori Tanah</a><br/>
                    &nbsp;&nbsp; 11) <a href="#" onclick="popupBangsaDetail();return false;">Statistik Permohonan Mengikut Bangsa Penerima Terperinci</a><br/>
                    &nbsp;&nbsp; 12) <a href="#" onclick="popupMohonPindahMilik();return false;">Laporan Terperinci Permohonan Pindah Milik</a><br/>
                    &nbsp;&nbsp; 13) <a href="#" onclick="popupPindahDanGadaian();return false;">Laporan Terperinci Permohonan Pindah Milik Dan Gadaian</a><br>
                    &nbsp;&nbsp; 14) <a href="#" onclick="popupLaporan15();return false;">Laporan Terperinci Permohonan Pajakan</a><br>
                    &nbsp;&nbsp; 15) <a href="#" onclick="popupLaporan16();return false;">Laporan Terperinci Permohonan Gadaian</a><br>
                    &nbsp;&nbsp; 16) <a href="#" onclick="popupPrestasiPindahMilik();return false;">Laporan Prestasi Permohonan Kebenaran Pindahmilik</a><br>
                </c:if>
            </font>
            <br/>
            <c:if test="${actionBean.kodNegeri eq '05'}">
                <c:if test="${actionBean.report_p_kod_caw ne '05' && actionBean.report_p_kod_caw ne '03'}">
                    <legend>Senarai Laporan Tanah Adat</legend><br><font color="black">
                        &nbsp;&nbsp; 1) &nbsp;&nbsp;<a href="#" onclick="popupLaporanAdat1();return false;">Statistik Hakmilik Mengikut Mukim (Adat)</a><br>
                        &nbsp;&nbsp; 2) &nbsp;&nbsp;<a href="#" onclick="popupLaporanAdat2();return false;">Statistik Permohonan Mengikut Kategori Tanah (Adat)</a><br>
                        &nbsp;&nbsp; 3) &nbsp;&nbsp;<a href="#" onclick="popupLaporanAdat3();return false;">Statistik Keputusan Permohonan Mengikut Jenis Permohonan Dan Pejabat (Adat)</a><br>
                        &nbsp;&nbsp; 4) &nbsp;&nbsp;<a href="#" onclick="popupLaporanAdat4();return false;">Laporan Kemajuan Permohonan Kebenaran Urusniaga (Adat)</a><br>
                        &nbsp;&nbsp; 5) &nbsp;&nbsp;<a href="#" onclick="popupLaporanAdat5();return false;">Graf Permohonan Mengikut Pejabat (Adat)</a><br>
                        &nbsp;&nbsp; 6) &nbsp;&nbsp;<a href="#" onclick="popupLaporanAdat6();return false;">Graf Permohonan Mengikut Jenis Kelulusan (Adat)</a><br>
                        &nbsp;&nbsp; 7) &nbsp;&nbsp;<a href="#" onclick="popupLaporanAdat7();return false;">Statistik Keputusan Mengikut Tempoh Permohonan (Adat)</a><br>
                        &nbsp;&nbsp; 8) &nbsp;&nbsp;<a href="#" onclick="popupLaporanAdat8();return false;">Statistik Jumlah Permohonan (Adat)</a><br>
                        &nbsp;&nbsp; 9) &nbsp;&nbsp;<a href="#" onclick="popupLaporanAdat9();return false;">Statistik Kelulusan Mengikut Kategori Tanah (Adat)</a><br>
                        &nbsp;&nbsp; 10) <a href="#" onclick="popupLaporanAdat10();return false;">Laporan Terperinci Permohonan Pindah Milik (Adat)</a><br>
                        &nbsp;&nbsp; 11) <a href="#" onclick="popupLaporanAdat11();return false;">Laporan Terperinci Permohonan Pajakan (Adat)</a><br>
                        &nbsp;&nbsp; 12) <a href="#" onclick="popupLaporanAdat12();return false;">Laporan Terperinci Permohonan Gadaian (Adat)</a><br>
                        <br/>
                    </font>
                </c:if>
            </c:if>
        </fieldset>
        <br/>        
        <fieldset class="aras1">
            <c:if test="${actionBean.kodNegeri eq '04'}">
            <legend>Senarai Ringkasan Permohonan</legend><br><font color="black">
                    &nbsp; 1) &nbsp;&nbsp;<a href="#" onclick="popupRingkasanKelulusanKM();return false;">Ringkasan Permohonan Kelulusan Ketua Menteri (Keputusan CM)</a><br>
                    &nbsp; 2) &nbsp;&nbsp;<a href="#" onclick="popupRingkasanKelulusanKM_PTG();return false;">Ringkasan Permohonan Kelulusan Ketua Menteri (Perakuan PTG)</a><br>
                    &nbsp; 3) &nbsp;&nbsp;<a href="#" onclick="popupRingkasanKelulusanPTG();return false;">Ringkasan Permohonan Kelulusan Pengarah Tanah Dan Galian (Keputusan PTG)</a><br>                                      
                    &nbsp; 4) &nbsp;&nbsp;<a href="#" onclick="popupRingkasanKelulusanPTD();return false;">Ringkasan Permohonan Kelulusan Pentadbir Tanah Daerah (PTD)</a><br>
                
            </font>
            <br/>
            </c:if>
        </fieldset>
                    
    </div>
</s:form>

