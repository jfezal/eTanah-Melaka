<%-- 
    Document   : notis_1
    Created on : Jan 6, 2010, 5:58:59 PM
    Author     : nurfaizati
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function blankValidation(){
        var result = true;
        if(($('#noDasar').val() == null || $('#noDasar').val() == "") && ($('#status').val() == null || $('#status').val() == "")){
            alert("Sila isi No. Rujukan Dasar dan Pilih Status terlebih dahulu.");
            $('#noDasar').focus();
            result = false;
        }else if($('#noDasar').val() == null || $('#noDasar').val() == ""){
            alert("Sila isi No. Rujukan Dasar terlebih dahulu.");
            $('#noDasar').focus();
            result = false;
        }else if($('#status').val() == null || $('#status').val() == ""){
            alert("Sila Pilih Status terlebih dahulu.");
            $('#status').focus();
            result = false;
        }
        return result;
    }

    function edit(id){
        window.open("${pageContext.request.contextPath}/hasil/surat_peringatan_notis6A?saveDasar&idDasar="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function edit1(id){
        window.open("${pageContext.request.contextPath}/hasil/notis?popupDetails&idHakmilik="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }
    
    function edit2(id){
        window.open("${pageContext.request.contextPath}/hasil/notis?popupDetails&idHakmilik="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function popup(id){
        window.open("${pageContext.request.contextPath}/hasil/papar_hakmilik_pihak?showForm&idHakmilik="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function edit1(f, id1){        
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var queryString = $(f).formSerialize();
        var reportName = "";
        if(${actionBean.kodNegeri eq 'melaka'}){
            reportName = "hasilSuratPeringatanML.rdf";
        }else{
            reportName = "hasilSuratPeringatan.rdf";
        }

        var url = '${pageContext.request.contextPath}/hasil/notis?savePeringatan&'+queryString+'&dasarTuntutanCukai.idDasar='+id1;
            $.get(url,
            function(data){
                if(data == "berjaya"){
                    var uri = "${pageContext.request.contextPath}/reportGeneratorServlet?reportName="+reportName+"&report_p_id_dasar="+id1;
                    window.open(uri, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                    $('#bilBulan').attr('disabled', 'disabled');
                    $('#bilHari').attr('disabled', 'disabled');
                }else{
                    alert("Terdapat Masalah Teknikal. Dokumen Tidak Berjaya Dijana.");
                }
                $.unblockUI();
            },'html');
    }
    
    function edit2(f, id1){    
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var queryString = $(f).formSerialize();
        var reportName = "";
        if(${actionBean.kodNegeri eq 'melaka'}){
            reportName = "hasilSuratPeringatanBil2.rdf";
        }else{
            reportName = "hasilSuratPeringatanBil2.rdf";
        }

        var url = '${pageContext.request.contextPath}/hasil/notis?savePeringatan&'+queryString+'&dasarTuntutanCukai.idDasar='+id1;
            $.get(url,
            function(data){
                if(data == "berjaya"){
                    var uri = "${pageContext.request.contextPath}/reportGeneratorServlet?reportName="+reportName+"&report_p_id_dasar="+id1;
                    window.open(uri, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                    $('#bilBulan').attr('disabled', 'disabled');
                    $('#bilHari').attr('disabled', 'disabled');
                }else{
                    alert("Terdapat Masalah Teknikal. Dokumen Tidak Berjaya Dijana.");
                }
                $.unblockUI();
            },'html');
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

    function popupDasar(){
        var f = document.notis;
        var url = f.action + '?popupCarianDasar&darimana=notis';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=1");
    }

    function refreshNotis(idDasar){
        var q = $('#form1').serialize();
        var url = document.notis.action + '?refreshBase&noDasar='+idDasar;
        window.location = url+q;
    }
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<s:form name="notis" beanclass="etanah.view.stripes.hasil.NotisActionBean" id="notis">
    <s:messages/>
    <s:errors/>&nbsp;
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian Senarai Hakmilik Dasar
            </legend>
            <p>
                <label><font color="red">*</font>No. Rujukan Dasar :</label>
                <s:text name="noDasar" id="noDasar" size="35" maxlength="30" onblur="this.value=this.value.toUpperCase();"/>
                <img alt='Klik Untuk Cari Dasar' border='0' height="16" width="16" src='${pageContext.request.contextPath}/pub/images/search-icon.png' class='rem'
                     id='carianDasar' onclick='popupDasar();return false;' onmouseover="this.style.cursor='pointer';" title="Carian Dasar.">
            </p>
            <p>
                <label> <font color="red">*</font>Hantar :</label>
                <s:select id="status" name="kodStatus" style="width:235px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodNotis}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p align="right">
                <s:submit class="btn" onclick="return blankValidation();" name="search" value="Cari"/>&nbsp;
                <s:button class="btn" name="reset" value="Isi Semula" onclick="clearText('notis');"/>
            </p>
            <%--<table width="100%">
                <tr>
                    <td align="right"><s:submit class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="search" value="Cari"/>&nbsp;
                        <s:reset class="btn" name="reset" value="Isi Semula" onclick="clearText('senarai_tugasan');"/></td>
                </tr>
            </table>--%>
        </fieldset>
        <br>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Senarai Hakmilik
                    </legend>

                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiDasarTuntutanCukaiHakmilik}" pagesize="10" cellpadding="0" cellspacing="0"
                                       requestURI="${pageContext.request.contextPath}/hasil/notis" id="line">
                         
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>

                            <display:column title="ID Hakmilik" >
                            <a href="#" onclick="popup('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a>
                            </display:column>
                            <display:column property="noRujukan" title="No Fail" class="${line_rowNum}" />
                            <display:column property="hakmilik.noLot" title="No Lot/ PT" class="${line_rowNum}" />
                            <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" class="${line_rowNum}" />
                            <display:column property="hakmilik.daerah.nama" title="Daerah" class="${line_rowNum}"/>
                        </display:table>
                    </div>
                    <p style="display:${actionBean.suratPeringatan}">
                        <label>Tempoh :</label>
                        <s:text id="bilBulan" name="bilBulan" size="5" maxlength="2" onkeyup="validateNumber(this,this.value);" /> Bulan
                    </p>
                    <p style="display:${actionBean.suratPeringatan}">
                        <label>&nbsp;</label>
                        <s:text id="bilHari" name="bilHari" size="5" maxlength="2" onkeyup="validateNumber(this,this.value);" /> Hari
                    </p>
                    <table border="0" bgcolor="green" width="100%" style="height:30px;">
                        <tr>
                            <td align="right">
                                <s:button name="cetak" onclick="edit1(this.form, '${line.dasarTuntutanCukai.idDasar}');" id="btn1" disabled="${actionBean.flag}" value="Peringatan Pertama" class="longbtn" style="display:${actionBean.suratPeringatan}"/>
                                <s:button name="cetak2" onclick="edit2(this.form, '${line.dasarTuntutanCukai.idDasar}');" id="btn2" disabled="${actionBean.flag}" value="Peringatan Terakhir" class="longbtn" style="display:${actionBean.suratPeringatan}"/>
                                <s:submit name="save6A" value="Sediakan Notis 6A" class="longbtn"  style="display:${actionBean.notis6A}" disabled="${actionBean.flag}" id="btn2"/>
                                <s:submit name="save8A" value="Sediakan Notis 8A" class="longbtn" style="display:${actionBean.notis8A}" disabled="${actionBean.flag}" id="btn3"/>
                                <s:submit name="notisGantian" value="Sediakan Notis Gantian" class="longbtn" style="display:${actionBean.notisGantian6A}" disabled="${actionBean.flag}" id="btn4"/>
                                <s:submit name="notisGantian8A" value="Sediakan Notis Gantian 8A" class="longbtn" style="display:${actionBean.notisGantian8A}" disabled="${actionBean.flag}" id="btn5"/>

                            </td>
                        </tr>
                    </table>
                </fieldset>
            </div>
    </div>
</s:form>
