<%--
    Document   : Maklumat_Penerimaan_Pembayaran_Pampasan
    Created on : Dec 1, 2011, 10:07:50 PM
    Author     : sitinorajar
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
        <script type="text/javascript">

            $(document).ready(function() {
                var bil =  ${fn:length(actionBean.ambilPampasanList)};
                var bil2 =  ${fn:length(actionBean.permohonanPihakList)};
                for (var i = 0; i < bil; i++){
                    var idPihakHadir = $("#idPihakHadir"+i).val();
                    alert("idPihakHadir :"+idPihakHadir);
                    for (var j = 0; j < bil2; j++){
                        var idPihak = $("#idPihak"+j).val();
                        alert("idPihak :"+idPihak);
                        if(idPihakHadir == idPihak){
                            alert("sama");
                            var statusDok = $("#statusDokDiambil"+i).val();
                            var tarikhDok = $("#tarikhDok"+i).val();
                            alert(statusDok);
                            alert(tarikhDok);
                            if(statusDok == "Y"){
                                document.getElementById("dokDiambilY"+i).checked = true;
                            }else{
                                document.getElementById("dokDiambilT"+i).checked = true;
                            }

                            if(tarikhDok != ""){
                                document.getElementById("tarikhTerima"+i).value = tarikhDok;
                            }
                        }
                    }

                }
            });


            function changeValue(value,index){
                alert(value);
                alert(index);
                if(value == "T"){
                    document.getElementById("dokDiambilY"+index).checked = false;
                    //document.getElementById("dokDiambilT"+index).checked = true;
                }else{
                    document.getElementById("dokDiambilT"+index).checked = false;
                    //document.getElementById("dokDiambilY"+index).checked = true;
                }

            }
        </script>

    </head>
    <body>

        <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPenerimaanpembayaranPampasanActionBean">

            <s:messages />
            <s:errors />
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>MAKLUMAT PENERIMAAN BAYARAN PAMPASAN</legend>
                    <br/><br/>

                    <p>
                        <label for="ID HAKMILIK">ID HAKMILIK :</label>
                        ${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}
                    </p>

                    <p>
                        <label for="NO LOT:">NO LOT :</label>
                        ${actionBean.hakmilikPermohonan.noLot}
                    </p>

                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonanPihakList}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="/pelupusan/maklumat_penerimaan_pembayaran_pampasan">
                            <display:column title="BIL">
                                ${line_rowNum}
                            </display:column>
                            <display:column title="NAMA">
                                ${line.pihak.nama}
                                <input type="hidden" name="idPihak${line_rowNum -1}" id="idPihak${line_rowNum -1}" value="${line.pihak.idPihak}">
                            </display:column>
                            <display:column property="pihak.noPengenalan" title="NO PENGENALAN" />
                            <display:column title="STATUS TERIMA">
                                <s:radio name="dokDiambilY${line_rowNum-1}" id="dokDiambilY${line_rowNum-1}" onclick="changeValue(this.value,'${line_rowNum-1}');" value="Y"/> Ya &nbsp;
                                <s:radio name="dokDiambilT${line_rowNum-1}" id="dokDiambilT${line_rowNum-1}" onclick="changeValue(this.value,'${line_rowNum-1}');" value="T"/> Tidak
                            </display:column>
                            <display:column title="TARIKH TERIMA">
                                <s:text formatPattern="dd/MM/yyyy" value="" class="datepicker" name="tarikhTerima${line_rowNum-1}" id="tarikhTerima${line_rowNum-1}"/>
                            </display:column>
                        </display:table>
                    </div  > 
                </fieldset>
            </div>

                   
<!--paparan-->
            <div id="ambilPampasan" style="visibility: hidden; display: none">
                <c:set value="0" var="i"/>
                <c:forEach items="${actionBean.ambilPampasanList}" var="senarai">
                    <input name="idPihakHadir${i}" id="idPihakHadir${i}" value="${senarai.perbicaraanKehadiran.pihak.pihak.idPihak}">
                    <input name="statusDokDiambil${i}" id="statusDokDiambil${i}" value="${senarai.dokDiambil}">
                    <input name="tarikh${i}" id="tarikh${i}" value="${senarai.tarikhDokDiambil}">
                    <s:text formatPattern="dd/MM/yyyy" value="${senarai.tarikhDokDiambil}" name="tarikhDok${i}" id="tarikhDok${i}"/>
                    <c:set var="i" value="${i+1}" />
                </c:forEach>
            </div>

                
                
            <p align="center">
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>

        </s:form>