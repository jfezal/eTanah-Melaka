<%-- 
    Document   : popup_tambah_hakisan
    Created on : Oct 31, 2012, 9:36:54 AM
    Author     : latifah.iskak
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>

<script type="text/javascript">
    
    $(document).ready(function() {
        self.opener.refreshPageAddHakmilik();
    });
    

    function validate(){
        if($("#statusCarian").val() != "" && $("#statusCarian").val() == "TW"){
            alert("Sila cari no hakmilik yang wujud");
            $("#hakmilik").focus(); 
            return false;
        }
        return true;
    }
    
    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $("#multipleHakmilikDiv",opener.document).replaceWith($('#multipleHakmilikDiv', $(data)));
        },'html');
        self.opener.refreshPageAddHakmilik();
        self.close();

    }
    
    function carianHakmilik(){
        var idHakmilik= $("#hakmilik").val();
        var from= $("#fromPage").val();
        var urlActionBean;
        if(from == "LTNH"){
            urlActionBean = "laporan_tanah?findHakmilikLTNH";
        }else{
            urlActionBean = "laporan_operasi_polis?findHakmilik";
        }
        //        alert(urlActionBean);
        if(idHakmilik != ""){
            $.get('${pageContext.request.contextPath}/penguatkuasaan/'+urlActionBean+'&idHakmilik='+idHakmilik,
            function(data){
                $("#resultHakmilikDiv").replaceWith($('#resultHakmilikDiv', $(data)));
                $("#msgInfoDiv").replaceWith($('#msgInfoDiv', $(data)));
                var status = $('#statusCarian').val();
                //                alert(status);
                //                if(status == "TW"){
                //                    alert("Id Hakmilik tidak wujud. Sila masukkan Id Hakmilik yang betul.");
                //                    return false;
                //                }
            }, 'html');

        }else{
            alert("Sila masukkan id hakmilik");
            $("#hakmilik").focus();
        }

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

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<c:if test="${actionBean.fromPage eq 'LTNH'}">
    <c:set var="callFromPage" value="etanah.view.penguatkuasaan.LaporanTanahActionBean"/>
</c:if>
<c:if test="${actionBean.fromPage ne 'LTNH'}">
    <c:set var="callFromPage" value="etanah.view.penguatkuasaan.LaporanOperasiPolisActionBean"/>
</c:if>

<s:form beanclass="${callFromPage}">
    <div id ="msgInfoDiv">
        <s:messages/>
        <s:errors/>
    </div>
    <s:hidden name="fromPage" id="fromPage"/>
    <c:if test="${edit}">
        <div class="subtitle">

            <fieldset class="aras1">
                <legend>Maklumat Hakmilik </legend>
                <div>
                    <div id="resultHakmilikDiv">
                        <p>
                            <label>Hakmilik :</label>
                            <s:text name="idHakmilik" id="hakmilik" onkeyup="this.value=this.value.toUpperCase();"/>
                            <font color="red" size="1">cth : 050501GM00000001</font>
                            <s:button class="btn" value="Cari" name="new" id="new" onclick="carianHakmilik();"/>
                        </p>

                        <s:hidden name="statusCarian" id="statusCarian"/>
                        <p>
                            <label>Kategori Tanah :</label>
                            <s:select name="hakmilik.kategoriTanah.kod" id="kodKategoriTanahCarian" disabled="true">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama"/>
                            </s:select>&nbsp;
                        </p>
                        <p>
                            <label>No Lot :</label>
                            <s:text name="hakmilik.noLot" id="noLot" maxlength="15" size="10" onkeyup="validateNumber(this,this.value);" readonly="true"/> &nbsp;
                        </p>
                        <p>
                            <label>Kod Lot:</label>
                            <s:select name="hakmilik.lot.kod" id="kodLotCarian" disabled="true">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                        <p>
                            <label>Luas :</label>
                            <s:text name="hakmilik.luas" id="luas" size="10" readonly="true"/> &nbsp;
                        </p>
                        <p>
                            <label>Kod Luas:</label>
                            <s:select name="hakmilik.kodUnitLuas.kod" id="kodLuasCarian" disabled="true">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                        <p>
                            <label>Daerah:</label>
                            <s:text name="hakmilik.daerah.nama" readonly="true"/> &nbsp;
                        </p>
                        <p>
                            <label>Bandar/Pekan/Mukim:</label>
                            <s:text name="hakmilik.bandarPekanMukim.nama" readonly="true"/> &nbsp;
                        </p>
                        <p>
                            <label>Jenis Penggunaan Tanah:</label>
                            <s:text name="hakmilik.maklumatAtasTanah" readonly="true"/> &nbsp;
                        </p>
                    </div>
                    <c:if test = "${actionBean.kodNegeri eq '04'}">
                        <p>
                            <label>Kod PBT :</label>
                            <s:select name="kodPBT" id="kodPBT">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodPBT}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                        <p>
                            <label>Kod Dun :</label>
                            <s:select name="kodDun" id="kodDun">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodDUN}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                        <p>
                            <label>Lokaliti :</label>
                            <s:text name="lokasi" size="50" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/> &nbsp;
                        </p>
                    </c:if>
                </div>
            </fieldset>
            <br/>
            <label>&nbsp</label>

            <s:button class="btn" name="simpanHakmilik" onclick="if(validate())save(this.name,this.form);" value="Simpan"/>
            <s:hidden name="idMH" id="idMH"/>
            <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </div>
    </c:if>

    <c:if test="${view}">
        <div class="subtitle">

            <fieldset class="aras1">
                <legend>Maklumat Hakmilik </legend>
                <div>
                    <p>
                        <label>Hakmilik :</label>
                        ${actionBean.hakmilik.idHakmilik}&nbsp;
                    </p>
                    <p>
                        <label>Kategori Tanah :</label>
                        ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
                    </p>
                    <p>
                        <label>No Lot :</label>
                        ${actionBean.hakmilik.noLot}&nbsp;
                    </p>
                    <p>
                        <label>Kod Lot:</label>
                        ${actionBean.hakmilik.lot.nama}&nbsp;
                    </p>
                    <p>
                        <label>Luas :</label>
                        ${actionBean.hakmilik.luas}&nbsp;
                    </p>
                    <p>
                        <label>Kod Luas:</label>
                        ${actionBean.hakmilik.kodUnitLuas.nama}&nbsp;
                    </p>
                    <p>
                        <label>Daerah:</label>
                        ${actionBean.hakmilik.daerah.nama}&nbsp;
                    </p>
                    <p>
                        <label>Bandar/Pekan/Mukim:</label>
                        ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
                    </p>
                    <p>
                        <label>Jenis Penggunaan Tanah:</label>
                        ${actionBean.hakmilik.maklumatAtasTanah}&nbsp;
                    </p>
                    <c:if test = "${actionBean.kodNegeri eq '04'}">
                        <p>
                            <label>Kod PBT :</label>
                            ${actionBean.multipleHakmilikPermohonan.kodPbt.nama}&nbsp;
                        </p>
                        <p>
                            <label>Kod Dun :</label>
                            ${actionBean.multipleHakmilikPermohonan.kodDUN.nama}&nbsp;
                        </p>
                        <p>
                            <label>Lokaliti :</label>
                            ${actionBean.multipleHakmilikPermohonan.lokaliti}&nbsp;
                        </p>
                    </c:if>
                </div>
            </fieldset>
            <br/>
            <label>&nbsp</label>
            <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </div>
    </c:if>

</s:form>
