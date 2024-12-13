<%-- 
    Document   : hasil_laporan_param
    Created on : May 18, 2010, 5:02:51 PM
    Author     : zadhirul.farihim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function doSubmit(f){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });

        var form = $(f).formSerialize();
        var url = form.replace(/&/g, "%26");
        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
        <%--f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
        f.submit();--%>
        $.unblockUI();
    }

    function dateValidation(id, value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("");
        }
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function validateYearLength(value){
        var plength = value.length;
        if(plength != 4){
            alert('"Tahun" yang dimasukkan salah.');
            $('#tahun').val("");
            $('#tahun').focus();
        }
    }
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>

<s:form beanclass="etanah.view.laporan.HasilLaporanActionBean">
    <s:hidden name="reportName"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Parameter</legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>

            <c:if test="${reportname eq 'HSLSenaraiIndukHakmilikMengikutMukim_002.rdf' or reportname eq 'HSLSenaraiHakmilikBatalMengikutMukim_003.rdf'
                          or reportname eq 'HSLSenaraiHmTunggakBgAmaunDanPdHinggaTarikh_002.rdf'
                          or reportname eq 'HSLSenaraiHakmilikAdaAmaunLebih001.rdf'
                          or reportname eq 'HSLSenaraiHakmilikNotis6ABelumBayar001.rdf'
                          or reportname eq 'HSLSenaraiHakmilikNotis6ABayar001.rdf'
                          or reportname eq 'HSLSenaraiHakmilikYangKurangBayar.rdf'
                          or reportname eq 'HSLSenaraiHakmilikYangLebihBayar.rdf'
                          or reportname eq 'HSLSenaraiHakmilikTelahBayar.rdf'
                          or reportname eq 'HSLSenaraiHakmilikYangBelumBayar.rdf'
                          or reportname eq 'HSLSenaraiHakmilikBeralamatDanTidakBeralamat.rdf'
                          or reportname eq 'HSLSenaraiIndexHakmilikIkutNama.rdf'
                          or reportname eq 'HSLSenaraiIndexLot001.rdf'
                          or reportname eq 'HSLSenaraiAlamatRagu.rdf'}">

                  <%-- set parameter p_kod_hakmilik for the following reports--%>
                  <c:if test="${reportname eq 'HSLSenaraiHakmilikAdaAmaunLebih001.rdf'
                                or reportname eq 'HSLSenaraiHmTunggakBgAmaunDanPdHinggaTarikh_002.rdf'}">
                        <p>
                            <label>Hakmilik :</label>
                            <s:select name="report_p_kod_hakmilik" style="width:145px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodHakmilik}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                  </c:if>

                  <%-- set parameter p_kod_daerah for the following reports--%>
                  <c:if test="${reportname eq 'HSLSenaraiIndexHakmilikIkutNama.rdf' or reportname eq 'HSLSenaraiIndexLot001.rdf'
                                or reportname eq 'HSLSenaraiHakmilikAdaAmaunLebih001.rdf' or reportname eq 'HSLSenaraiAlamatRagu.rdf'
                                or reportname eq 'HSLSenaraiHmTunggakBgAmaunDanPdHinggaTarikh_002.rdf'}">
                        <p>
                            <label>Daerah :</label>
                            <s:select name="report_p_kod_daerah" style="width:145px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                  </c:if>
                  <%-- set parameter p_kod_mukim--%>
                  <p>
                      <label>Mukim :</label>
                      <s:select name="report_p_kod_mukim" style="width:145px;">
                          <s:option value="">--Sila Pilih--</s:option>
                          <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>

                      </s:select>
                  </p>

                  <%-- set parameter p_kod_negeri for the following reports--%>
                  <c:if test="${reportname eq 'HSLSenaraiHmTunggakBgAmaunDanPdHinggaTarikh_002.rdf'}">
                      <p>
                          <label>Negeri :</label>
                          <s:select name="report_p_kod_negeri" style="width:145px;">
                              <s:option value="">--Sila Pilih--</s:option>
                              <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                          </s:select>
                      </p>
                  </c:if>



                  <c:if test="${reportname eq 'HSLSenaraiIndukHakmilikMengikutMukim_002.rdf' or reportname eq 'HSLSenaraiHakmilikBatalMengikutMukim_003.rdf'}">
                      <p>
                          <label>Hakmilik :</label>
                          <s:select name="report_p_kod_hakmilik" style="width:145px;">
                              <s:option value="">--Sila Pilih--</s:option>
                              <s:options-collection collection="${listUtil.senaraiKodHakmilik}" label="nama" value="kod"/>
                          </s:select>
                      </p>
                      <c:if test="${reportname eq 'HSLSenaraiHmTunggakBgAmaunDanPdHinggaTarikh_002.rdf'}">
                          <p>
                              <label>Daerah :</label>
                              <s:select name="report_p_kod_daerah" style="width:145px;">
                                  <s:option value="">--Sila Pilih--</s:option>
                                  <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod"/>
                              </s:select>
                          </p>

                          <p>
                              <label>Negeri :</label>
                              <s:select name="report_p_kod_negeri" style="width:145px;">
                                  <s:option value="">--Sila Pilih--</s:option>
                                  <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>

                              </s:select>
                          </p>
                      </c:if>
                  </c:if>

            </c:if>
            <c:if test="${reportname eq 'HSLSenaraiTerimaan.rdf' or reportname eq 'SPOCLaporanPenjenisanHasil_NS.rdf'
                          or reportname eq 'HSLKemajuanKutipanCukaiTanahDendaNotisIkutMukim.rdf'
                          or reportname eq 'HSLSenaraiKutipanHasil_Kod_Jenis_Hasil_NS.rdf'
                          or reportname eq 'HSLSenaraiHakmilikLuarYgDikutip.rdf'
                          or reportname eq 'HSLSenaraiHakmilikYangTelahDibatalkanMengikutTarikhBatal_005'}">
                  <p>
                      <label>Tarikh Mula :</label>
                      <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                              onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                  </p>
                  <p>
                      <label>Tarikh Tamat :</label>
                      <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                              onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                  </p>
                  <c:if test="${reportname eq 'HSLSenaraiTerimaan.rdf' or reportname eq 'HSLKemajuanKutipanCukaiTanahDendaNotisIkutMukim.rdf'}">
                      <p>
                          <label>Dimasuk :</label>
                          <s:text id="dimasuk" name="report_p_dimasuk"/>&nbsp;
                      </p>
                      <p>
                          <label>Status Kutipan :</label>
                          <s:text id="status_kutipan" name="report_p_status_kutipan"/>&nbsp;
                      </p>
                  </c:if>
                  <c:if test="${reportname eq 'SPOCLaporanPenjenisanHasil_NS.rdf' or reportname eq 'HSLSenaraiKutipanHasil_Kod_Jenis_Hasil_NS.rdf'
                                or reportname eq 'HSLSenaraiHakmilikLuarYgDikutip.rdf'}">
                        <p>
                            <label>Cawangan :</label>
                            <s:select name="report_p_kod_caw" style="width:145px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                            </s:select>
                        </p>
                  </c:if>
            </c:if>

            <c:if test="${reportname eq 'hasilRayuanKurangCukai.rdf' or reportname eq 'hasilNotis6A.rdf'
                          or reportname eq 'hasilSuratPeringatan.rdf' or reportname eq 'hasilNotis8A.rdf'
                          or reportname eq 'hasilWartaKerajaan.rdf' or reportname eq 'hasilNotisGantian.rdf'}">
                  <p>
                      <label>ID Mohon :</label>
                      <s:text id="id_mohon" name="report_p_id_mohon"/>&nbsp;
                  </p>
                  <c:if test="${reportname eq 'hasilNotis6A.rdf' or reportname eq 'hasilSuratPeringatan.rdf'}">
                      <p>
                          <label>ID Dasar :</label>
                          <s:text id="id_dasar" name="report_p_id_dasar"/>&nbsp;
                      </p>
                  </c:if>
            </c:if>
            <c:if test="${reportname eq 'hasilSuratGantiCekTakLakuResit.rdf'}">
                <p>
                    <label>ID Resit :</label>
                    <s:text id="id_resit" name="report_p_id_resit"/>&nbsp;
                </p>
            </c:if>
            <c:if test="${reportname eq 'hasilPembatalanNotis6A.rdf'}">
                <p>
                    <label>ID Hakmilik :</label>
                    <s:text id="id_hakmilik" name="report_p_id_hakmilik"/>&nbsp;
                </p>
                <c:if test="${reportname eq 'hasilPembatalanNotis6A.rdf'}">
                    <p>
                        <label>ID Dasar :</label>
                        <s:text id="id_dasar" name="report_p_id_dasar"/>&nbsp;
                    </p>
                </c:if>
            </c:if>
            <c:if test="${reportname eq 'HSLSenaraiResitBatal.rdf'}">
                <p>
                    <label>Bulan :</label>
                    <s:text id="bulan" name="report_p_bulan"/>&nbsp;
                </p>
                <p>
                    <label>Tahun :</label>
                    <s:text id="tahun" name="report_p_tahun"/>&nbsp;
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSLLaporanLogoffKutipan.rdf'}">
                <p>
                    <label>Tarikh Kutipan :</label>
                    <s:text id="trh_kutipan" name="report_p_trh_kutipan" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label>Kaunter :</label>
                    <s:text id="kaunter" name="report_p_kaunter"/>&nbsp;
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSLSuratAkuanBerkanun.rdf'}">
                <p>
                    <label>No. Rujukan :</label>
                    <s:text id="no_ruj" name="report_p_no_ruj"/>&nbsp;
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSLLaporanTerimaanPos.rdf'}">
                <p>
                    <label>Daerah :</label>
                    <s:select name="report_p_kod_daerah" style="width:145px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>


            <br>
            <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
            </p>

        </fieldset >
    </div>

</s:form>
