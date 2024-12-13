<%-- 
    Document   : StatusAward
    Created on : 20-Jul-2011, 18:56:04
    Author     : nordiyana
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
    function refreshPagePenerimaanBorang(){
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }

    function validationTanah() {
        var tanahItem = $("#tanahItem").val();
        var tanahAmaun = $("#tanahAmaun").val();

        if(tanahItem == ""){
            alert('Sila pilih " Item : " terlebih dahulu.');
            $("#tanahItem").focus();
            return false;
        }

        if(tanahAmaun == ""){
            alert('Sila pilih " RM : " terlebih dahulu.');
            $("#tanahAmaun").focus();
            return false;
        }
        return true;
    }

    function validationBngn() {
        var bngnItem = $("#bngnItem").val();
        var bngnAmaun = $("#bngnAmaun").val();

        if(bngnItem == ""){
            alert('Sila pilih " Item : " terlebih dahulu.');
            $("#bngnItem").focus();
            return false;
        }

        if(bngnAmaun == ""){
            alert('Sila pilih " RM : " terlebih dahulu.');
            $("#bngnAmaun").focus();
            return false;
        }
        return true;
    }

    function validationLain() {
        var lainItem = $("#lainItem").val();
        var lainAmaun = $("#lainAmaun").val();

        if(lainItem == ""){
            alert('Sila pilih " Item : " terlebih dahulu.');
            $("#lainItem").focus();
            return false;
        }

        if(lainAmaun == ""){
            alert('Sila pilih " RM : " terlebih dahulu.');
            $("#lainAmaun").focus();
            return false;
        }
        return true;
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
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
    function removeNilai(idPenilaian)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/maklumatPampasan?deleteNilai&idPenilaian='
                +idPenilaian;
            $.get(url,
            function(data){
                $('#page_div').html(data);
    <%--self.opener.refreshPageTanahRizab();--%>
                },'html');
            }
        }
</script>
<s:form beanclass="etanah.view.stripes.pengambilan.StatusAwardActionBean">
    <div  id="hakmilik_details">
        <s:messages/>
        <s:errors/>

        <fieldset class="aras1"><br/>
            <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
            <div align="center">

                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/maklumatPampasan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.StatusAwardActionBeanMahkamah"
                                event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                            <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}</s:link>
                    </display:column>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                </display:table>
            </div>
        </fieldset>
        <br /><br />

        <c:if test="${actionBean.hakmilik ne null}">
            <fieldset class="aras1">
                <legend>Tuan Tanah</legend><br />
                <div align="center">


                    <p align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiPerbicaraanKehadiran}" cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/status" id="line" pagesize="15">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column property="pihak.pihak.nama" title="Pemilik Berdaftar" />
                            <display:column property="pihak.pihak.noPengenalan" title="No KP" />
                            <display:column title="Bahagian" >
                                ${line.pihak.syerPembilang}/${line.pihak.syerPenyebut}
                            </display:column>
                            <display:column title="Status Penerimaan Award">
                                ${line.keterangan}
                                <%-- <s:radio name="status[${line_rowNum-1}]" value="Terima" disabled="true"/>Terima
                                 <s:radio name="status[${line_rowNum-1}]" value="Mahkamah" disabled="true"/>Mahkamah
                                 <s:radio name="status[${line_rowNum-1}]" value="Amanah" disabled="true"/>Amanah Raya--%>
                            </display:column>
                          <%--   <display:column title="Maklumat Waris" >
                                <%--<s:textarea size="5" name="catatan[${line_rowNum - 1}]" id="catatan${line_rowNum - 1}"/>&nbsp;--%>
                          <%--      <s:textarea  rows="5"  cols="30" name="catatan[${line_rowNum - 1}]" id="catatan${line_rowNum - 1}"/>
                                <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="count"/>
                            </display:column>--%>
                            <display:column title="Pampasan (RM)">
                                <%-- <c:set value="${line.pihak.syerPembilang}" var="a"/>
                                  <c:set value="${line.pihak.syerPenyebut}" var="b"/>
                                  <c:set value="${actionBean.hakmilikPermohonan.luasTerlibat}" var="c"/>
                                  <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)/(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                                  <c:set value="${a/b*c}" var="e"/>
                                  <c:set value="${e*d}" var="f"/>--%>
                                <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="list">
                                    <c:if test="${line.pihak.pihak.idPihak == list.pihak.idPihak}">
                                        <c:set value="${line.pihak.syerPembilang}" var="a"/>
                                        <c:set value="${line.pihak.syerPenyebut}" var="b"/>
                                        <c:set value="${actionBean.hakmilikPermohonan.luasTerlibat}" var="c"/>
                                        <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                                        <c:set value="${d*(a/b)}" var="e"/>
                                        <c:set value="${e}" var="f"/>
                                    </c:if>
                                </c:forEach>
                                
                                <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="list">
                                    <c:if test="${line.pihak.pihak.idPihak == list.pihak.pihak.idPihak}">
                                        <c:set var="B" value="0"/>
                                        <c:forEach items="${list.senaraiPenilaian}" var="list1">
                                            <c:set value="${B + list1.amaun}" var="B"/>
                                        </c:forEach>
                                        <c:set value="${B}" var="g"/>
                                    </c:if>
                                </c:forEach>
                                <fmt:formatNumber pattern="#,##0.00" value="${f+g}"/>
                            </display:column>

                            <%-- <display:column title="Jumlah (RM)">
                                 <c:set value="${line.pihak.syerPembilang}" var="a"/>
                                 <c:set value="${line.pihak.syerPenyebut}" var="b"/>
                                 <c:set value="${actionBean.hakmilikPermohonan.luasTerlibat}" var="c"/>
                                 <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                                 <c:set value="${d*(a/b)}" var="e"/>
                                 <c:set value="${e}" var="f"/>

                                <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="list">
                                    <c:if test="${line.pihak.idPihak == list.pihak.pihak.idPihak}">
                                        <c:set var="B" value="0"/>
                                        <c:forEach items="${list.senaraiPenilaian}" var="list1">
                                            <c:set value="${B + list1.amaun}" var="B"/>
                                        </c:forEach>
                                        <c:set value="${B}" var="g"/>
                                    </c:if>
                                </c:forEach>
                                <fmt:formatNumber pattern="#,##0.00" value="${f+g}"/>
                            <%--<fmt:formatNumber pattern="#,##0.00" value="${a+b+c}"/>
                        </display:column>--%>

                        </display:table>
                        <br><br>

                    </p>
                    <%--<s:button class="btn" value="Simpan" name="simpanStatus" id="new" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;--%>
                    <%--<s:button class="btn" value="Hapus" name="remove" id="remove" onclick="deleteKehadiran('line1');"/>&nbsp;--%>
                </div>
                <br /><br />
            </fieldset><br />
            <%-- <fieldset class="aras1">
                <legend>Pihak Berkepentingan Tidak Berdaftar</legend><br />
                <div align="center">
                    Sila masukkan nilaian untuk setiap Pihak Berkepentingan.

                      <p align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiHadir}" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/status" id="line1" pagesize="5">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="nama" title="Pemilik Berdaftar" />
                    <display:column property="noPengenalan" title="No KP" />
                    <display:column title="Bahagian" >
                        ${line.pihak.syerPembilang}/${line.pihak.syerPenyebut}
                    </display:column>
                    <display:column title="Status Penerimaan Award">
                         <s:radio name="status1[${line1_rowNum-1}]" value="Terima"/>Terima
                         <s:radio name="status1[${line1_rowNum-1}]" value="Mahkamah"/>Mahkamah
                         <s:radio name="status1[${line1_rowNum-1}]" value="Amanah"/>Amanah Raya
                    </display:column>
                    <display:column title="Catatan" >
                        <s:textarea size="5" name="catatan[${line_rowNum - 1}]" id="catatan${line_rowNum - 1}"/>&nbsp;
                        <s:textarea  rows="5"  cols="30" name="catatan1[${line1_rowNum - 1}]" id="catatan1${line1_rowNum - 1}"/>
                        <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="count"/>
                    </display:column>
                    <display:column title="Pampasan (RM)">
                                    <c:forEach items="${actionBean.senaraiHadir}" var="list">
                                    <c:set var="T" value="0"/>
                                    <c:set var="B" value="0"/>
                                    <c:set var="L" value="0"/>
                                    <c:forEach items="${list.senaraiPenilaian}" var="list1">
                                        <c:if test="${list1.jenis eq 'T'}">
                                            <c:set value="${T + list1.amaun}" var="T"/>
                                        </c:if>
                                        <c:if test="${list1.jenis eq 'B'}">
                                            <c:set value="${B + list1.amaun}" var="B"/>
                                        </c:if>
                                        <c:if test="${list1.jenis eq 'L'}">
                                            <c:set value="${L + list1.amaun}" var="L"/>
                                        </c:if>
                                    </c:forEach>
                                    <b>Bangunan : RM <c:out value="${B}"/><br/></b>
                                    <b>Lain - lain : RM <c:out value="${L}"/></b>
                                    RM <fmt:formatNumber pattern="#,##0.00" value="${B+L}"/>
                                </c:if>
                        </c:forEach>
                    </display:column>
                </display:table>
                         <br><br>

            </p>
                        <s:button class="btn" value="Simpan" name="simpanStatusPBT" id="new" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                        <s:button class="btn" value="Hapus" name="remove" id="remove" onclick="deleteKehadiran('line1');"/>&nbsp;
              </div>
                <br /><br />
          </fieldset>--%>
        </c:if>

    </div>
</s:form>