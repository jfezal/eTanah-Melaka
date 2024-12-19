<%--
    Document   : edit_pemohon
    Created on : Nov 10, 2009, 4:23:38 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/formatCurrency.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready( function(){

        var val = $('#gajiPemohon').val();
        if(val != ''){
            convert(val, 'gajiPemohon');
        }

        var val2 = $('#gajiPenerima').val();
        if(val2 != ''){
            convert(val2, 'gajiPenerima');
        }

        var v = '${actionBean.pihak.jenisPengenalan.kod}';
        if(v == "B"){
            var icNo = '${actionBean.pihak.noPengenalan}';
            var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), 19 + icNo.substring(0,2));
            $('#umur').val(age);
        }
    });

    function convert(val, id){
        var amaun = CurrencyFormatted(val);
        amaun = CommaFormatted(amaun);
        $('#'+id).val(amaun);
    }

    function save(event, f){

        $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            <c:choose>
                <c:when test="${multiple}">
                            $('#div_content',opener.document).html(data);
                </c:when>
                <c:otherwise>
                            $('#page_div',opener.document).html(data);
                </c:otherwise>
            </c:choose>
                $.unblockUI();
                self.close();
            },'html');
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
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.stripes.common.PihakBerkepentinganActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="hakmilik" value="${hakmilik}"/>
            <legend>
                <c:if test="${penerima}"> Maklumat Penerima</c:if>
                <c:if test="${!penerima}">Maklumat Pemohon</c:if>
            </legend>
            <p>
                <label>Nama :</label>
                <c:if test="${penerima}">
                    <s:text name="pihak.nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </c:if>
                <c:if test="${!penerima}">
                    ${actionBean.pihak.nama}
                </c:if>
                &nbsp;<s:hidden name="pihak.idPihak"/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                ${actionBean.pihak.noPengenalan}&nbsp;
            </p>
            <c:if test="${!penerima}">
                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                    <p>
                        <label>Umur :</label>
                        <s:text name="pemohon.umur"  id="umur" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label>Pekerjaan :</label>
                        <s:text name="pemohon.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Pendapatan Bulanan (RM) :</label>
                        <s:text name="pemohon.pendapatan" size="40" onkeyup="validateNumber(this,this.value);" id="gajiPemohon"
                                onchange="convert(this.value, this.id);" formatPattern="#,###.00"/>
                    </p>
                    <p>
                        <label>Status Perkahwinan :</label>
                        <s:select name="pemohon.statusKahwin">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option>Berkahwin</s:option>
                            <s:option>Bujang</s:option>
                            <s:option>Duda</s:option>
                            <s:option>Ibu Tunggal</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Tanggungan :</label>
                        <s:text name="pemohon.tangungan" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                </c:if>
                <p>
                    <label>Hubungan :</label>
                    <s:select name="pemohon.kaitan">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option>IBU BAPA KEPADA ANAK</s:option>
                        <s:option>ANAK KEPADA IBU BAPA</s:option>
                        <s:option>SUAMI KEPADA ISTERI</s:option>
                        <s:option>ISTERI KEPADA SUAMI</s:option>
                        <s:option>SAUDARA-MARA</s:option>
                        <s:option>PENJUAL / PEMBELI</s:option>
                        <s:option>LAIN-LAIN</s:option>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${penerima}">
                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                    <p>
                        <label>Umur :</label>
                        <s:text name="permohonanPihak.umur" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label>Pekerjaan :</label>
                        <s:text name="permohonanPihak.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Pendapatan Bulanan (RM) :</label>
                        <s:text name="permohonanPihak.pendapatan" size="40" onkeyup="validateNumber(this,this.value);" id="gajiPenerima"
                                onchange="convert(this.value, this.id);" formatPattern="#,###.00"/>
                    </p>
                    <p>
                        <label>Status Perkahwinan :</label>
                        <s:select name="permohonanPihak.statusKahwin">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option>Berkahwin</s:option>
                            <s:option>Bujang</s:option>
                            <s:option>Duda</s:option>
                            <s:option>Ibu Tunggal</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Tanggungan :</label>
                        <s:text name="permohonanPihak.tangungan" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                </c:if>
                    <p>
                        <label>Jenis Pihak Berkepentingan</label>
                        <s:select name="permohonanPihak.jenis.kod" style="width:400px" id="jenis_pihak" onchange="javaScript:changePgAmanah(this.options[selectedIndex].text);">
                                    <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod" />
                                </s:select>
                    </p>
                <p>
                    <label>Hubungan :</label>
                    <s:select name="permohonanPihak.kaitan">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option>IBU BAPA KEPADA ANAK</s:option>
                        <s:option>ANAK KEPADA IBU BAPA</s:option>
                        <s:option>SUAMI KEPADA ISTERI</s:option>
                        <s:option>ISTERI KEPADA SUAMI</s:option>
                        <s:option>SAUDARA-MARA</s:option>
                        <s:option>PENJUAL / PEMBELI</s:option>
                        <s:option>LAIN-LAIN</s:option>
                    </s:select>
                </p>
            </c:if>
            <p>
                <label>Warganegara :</label>
                <s:select name="pihak.wargaNegara.kod" style="width:200px" id="kod_warganegara">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiWarganegara}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>Alamat Surat-Menyurat :</label>
                <s:text name="pihak.suratAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> Bandar :</label>
                <s:text name="pihak.suratAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="pihak.suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="pihak.suratNegeri.kod" >
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <c:if test="${!penerima}">
                    <label>&nbsp;</label>
                    <s:button name="simpanPihak" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                </c:if>
                <c:if test="${penerima}">
                    <label>&nbsp;</label>
                    <s:button name="simpanEditPenerima" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                </c:if>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset >
    </div>

</s:form>