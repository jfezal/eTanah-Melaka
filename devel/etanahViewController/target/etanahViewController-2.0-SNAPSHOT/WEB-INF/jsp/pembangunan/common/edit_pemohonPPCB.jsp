<%--
    Document   : edit_pemohon
    Created on : Apr 6, 2010, 6:25:32 PM
    Author     : nursyazwani
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

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="/etanah/pub/js/ageCalculator.js"></script>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
                self.opener.refreshPagePemohon();
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

     function copyAdd(){
     if($('input[name=checkAlamat]').is(':checked')){
         $('#suratAlamat1').val($('#alamat1').val());
         $('#suratAlamat2').val($('#alamat2').val());
         $('#suratAlamat3').val($('#alamat3').val());
         $('#suratAlamat4').val($('#alamat4').val());
         $('#suratPoskod').val($('#poskod').val());
         $('#suratNegeri').val($('#negeri').val());
     }else{
         $('#suratAlamat1').val('');
         $('#suratAlamat2').val('');
         $('#suratAlamat3').val('');
         $('#suratAlamat4').val('');
         $('#suratPoskod').val('');
         $('#suratNegeri').val('');
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

    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        });

</script>

<s:form beanclass="etanah.view.stripes.pembangunan.PihakBerkepentinganPPCBActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <c:if test="${penerima}"> Maklumat Penerima</c:if>
                <c:if test="${!penerima}">Maklumat Pemohon</c:if>
            </legend>
            <p>
                <label>Nama :</label>
                ${actionBean.pihak.nama}&nbsp;<s:hidden name="pihak.idPihak"/>
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                ${actionBean.pihak.noPengenalan}&nbsp;
            </p>
            <%--<p>
                <label>Alamat Surat Menyurat :</label>
                <s:checkbox name="statusFlag" value="Y" id="statusFlag" />
            </p>--%>
            <p>
                <label>Bangsa / Jenis Syarikat :</label>
                <s:select name="pihak.bangsa.kod" style="width:262px">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>Alamat :</label>
                <s:text name="alamat1" id="alamat1" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="alamat2" id="alamat2" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="alamat3"  id="alamat3" size="40"/>
            </p>
            <p>
                <label>Bandar :</label>
                <s:text name="alamat4"  id="alamat4" size="40"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="poskod" id="poskod" size="40" maxlength="5"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="negeri" id="negeri" style="width:255px;">
                    <s:option>Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>

            <c:if test="${actionBean.checkAlamat eq 'Yes'}">
                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="checkAlamat" checked="checked" name="checkAlamat" value="Yes" onclick="copyAdd();"/>
                            <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.checkAlamat eq 'No'}">
                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="checkAlamat" name="checkAlamat" value="No" onclick="copyAdd();"/>
                            <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                        </p>
                    </c:if>
                    <%--<c:if test="${emptypemohon}">
                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="checkAlamat" name="checkAlamat" value="No" onclick="copyAdd();"/>
                            <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                        </p>
                    </c:if>--%>


                    <p>
                        <label for="alamat">Alamat Surat-Menyurat:</label>
                        <s:text name="suratAlamat1" id="suratAlamat1" size="40"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="suratAlamat2" id="suratAlamat2" size="40"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="suratAlamat3" id="suratAlamat3" size="40"/>
                    </p>
                    <p>
                        <label> Bandar :</label>
                        <s:text name="suratAlamat4" id="suratAlamat4" size="40"/>
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber2(this,this.value);"/>
                    </p>

                    <p>
                        <label for="suratNegeri">Negeri :</label>
                        <s:select name="suratNegeri" id="suratNegeri" style="width:255px;">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
                    </p>


           <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'N' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">
            <p>
                <label>Modal Berbayar :</label>
                <s:text name="pihak.modalBerbayar" id="modalBerbayar" size="40" maxlength="15" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Modal Dibenarkan :</label>
                <s:text name="pihak.modalDibenar" id="modalDibenar" size="40" maxlength="15" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Tarikh Daftar Syarikat :</label>
                <s:text name="pihak.tarikhLahir" id="datepicker" class="datepicker" size="40" formatPattern="dd/MM/yyyy" />
            </p>
           </c:if>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanPihak" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset >
    </div>

</s:form>
