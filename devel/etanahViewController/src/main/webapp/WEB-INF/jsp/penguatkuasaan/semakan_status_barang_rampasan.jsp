<%--
    Document   : semakan_status_barang_rampasan
    Created on :Aug 8, 2011, 2:03:22 PM
    Author     : latifah.iskak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
        <title>Carian barang rampasan</title>

    </head>
    <body>
        <script type="text/javascript">
         

            function validate(){
                return true;
            }

            function findBarang(){
                var id = $('#idMohon').val();
                if(id == "")
                {
                    alert("Sila masukkan id permohonan terlebih dahulu.");
                    $('#idMohon').focus();
                    return false;
                }else{
                    var url = '${pageContext.request.contextPath}/penguatkuasaan/status_barang_rampasan?checkStatus&id='+id;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');
                }

            }
            
            function popup(idBarang){
                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?viewBarangDetail&idBarang='+idBarang;
                window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
            }



        </script>
        <s:messages />
        <s:errors />

        <s:form beanclass="etanah.view.penguatkuasaan.UtilitiStatusBarangRampasanActionBean" name="form1">

            <fieldset class="aras1">

                <legend>Carian barang rampasan</legend>
                <br>
                <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                    <input type="text" name="idPermohonan" id="idMohon" onkeyup="this.value=this.value.toUpperCase();"/>
                    <s:submit name="checkStatus" value="Cari" class="btn"/>
                </p>

                <p>
                    <label>Id Permohonan :</label>
                    ${actionBean.idPermohonan} &nbsp;
                </p>


                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>
                            Maklumat Barang Rampasan
                        </legend>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.listBarangRampasan}" cellpadding="0" cellspacing="0" id="line"
                                           requestURI="/penguatkuasaan/status_barang_rampasan">
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column title="Barang Rampasan"><u><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a></u></display:column>
                                <display:column title="Kuantiti">
                                    <c:if test="${line.kodKategoriItemRampasan.kod eq 'K'}">
                                        1
                                    </c:if>
                                    <c:if test="${line.kodKategoriItemRampasan.kod eq 'B'}">
                                        ${line.kuantiti}
                                    </c:if>
                                </display:column>
                                <display:column title="Unit Kuantiti">
                                    <c:if test="${line.kodKategoriItemRampasan.kod eq 'K'}">
                                        Sebuah
                                    </c:if>
                                    <c:if test="${line.kodKategoriItemRampasan.kod eq 'B'}">
                                        ${line.kuantitiUnit}
                                    </c:if>
                                </display:column>
                                <%--<display:column title="Tempat Simpanan" property="tempatSimpanan"></display:column>--%>
                                <display:column title="Status">
                                    <input name="idBarang${line_rowNum-1}" value="${line.idBarang}" type="hidden">
                                    <input name="idOperasi" value="${line.operasi.idOperasi}" type="hidden">
                                    
                                    <s:select name="status${line_rowNum-1}" id="status" value="${line.status.kod}" disabled="true">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${actionBean.senaraiKodStatus}" label="nama" value="kod" />
                                    </s:select>&nbsp;
                                </display:column>
                                <display:column title="Status Baru">
                                    <s:select name="statusBaru${line_rowNum-1}" id="statusBaru" value="">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${actionBean.senaraiKodStatus}" label="nama" value="kod" />
                                    </s:select>&nbsp;
                                </display:column>
                                <display:column title="Ulasan">
                                    <s:textarea name="ulasan${line_rowNum-1}" id="ulasan${line_rowNum-1}" value="${line.ulasan}"  rows="5" cols="30"/>
                                </display:column>
                            </display:table>
                            <table>
                                <tr>
                                    <td align="right">
                                       <s:submit class="btn" value="Simpan" name="simpan" id="simpan" onclick="return validate()"/>

                                    </td>
                                   
                                
                                </tr>
                            </table>
                            <br>
                        </div>
                    </fieldset>
                </div>

            </fieldset>
        </s:form>

    </body>
</html>
