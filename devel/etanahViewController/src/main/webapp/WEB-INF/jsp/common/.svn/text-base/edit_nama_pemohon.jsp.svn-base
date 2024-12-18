<%-- 
    Document   : edit_nama_pemohon
    Created on : Dec 21, 2009, 7:03:37 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
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
                $('#page_div',opener.document).html(data);
                $.unblockUI();
                self.close();
            },'html');
        }

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.daftar.PihakKepentinganAction">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pihak Terlibat</legend>
            
            <c:if test="${!empty moreThanOneHakmilik}">
                <p>
                    <label>&nbsp;</label>
                    <font color="red" size="2">
                        <input type="checkbox" name="copyToAll" value="1"/>
                        <em>Sila klik jika sama untuk semua hakmilik.</em>
                    </font>
                </p>
            </c:if>

            <div class="content" align="center">
                <table class="tablecloth" cellpadding="0" cellspacing="0">
                <thead>
                <tr>
                    <th>Bil</th>
                    <th>Jenis Medan</th>
                    <th>Maklumat Lama</th>
                    <th>Maklumat Baru</th>
                </tr>
                </thead>
                <tbody>
                    <tr class="odd">
                        <td>1.</td>
                        <td>Nama</td><s:hidden name="idHakmilik"/><s:hidden name="namaLama" value="${actionBean.pihak.nama}"/>
                        <td>${actionBean.pihak.nama}&nbsp;<s:hidden name="idPihak" value="${actionBean.pihak.idPihak}"/></td>
                        <td>
                                <%--<s:text name="nama" onkeyup="this.value=this.value.toUpperCase();"/>--%>
                                <s:textarea name="nama" onkeyup="this.value=this.value.toUpperCase()" rows="5" cols="50" value="${nama}"/>
                        </td>
                    </tr>
                    <tr class="even">
                        <td>2.</td>
                        <td>Jenis Pengenalan</td>
                        <td>${actionBean.pihak.jenisPengenalan.nama}&nbsp;</td><s:hidden name="jeniskpLama" value="${actionBean.pihak.jenisPengenalan.kod}"/>
                        <td>
                            <s:select name="jeniskp" value="${jeniskp}">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </td>
                    </tr>
                    <tr class="odd">
                        <td>3.</td>
                        <td>No Pengenalan</td>
                        <td>${actionBean.pihak.noPengenalan}&nbsp;</td><s:hidden name="nokpLama" value="${actionBean.pihak.noPengenalan}"/>
                        <td><s:text name="nokp" onkeyup="this.value=this.value.toUpperCase();" value="${nokp}"/></td>
                    </tr>
                    <tr class="even">
                        <td>4.</td>
                        <td>Warganegara</td>
                        <td>${actionBean.pihak.wargaNegara.nama}&nbsp;</td><s:hidden name="kodWarganegaraLama" value="${actionBean.pihak.wargaNegara.kod}"/>
                        <td>
                            <s:select name="kodWarganegara" value="${kodWarganegara}">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiWarganegara}" label="nama" value="kod"/>
                            </s:select>
                        </td>
                    </tr>
                    
                </tbody>
                
            </table>
            </div>


            <%--<p>
                <label>Nama Lama :</label>
                ${actionBean.pihak.nama}&nbsp;<s:hidden name="pihak.idPihak"/>
            </p>
            <p>
                <label>Nama Baru :</label>
                <s:text name="nama" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Jenis Pengenalan Lama :</label>
                ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
            </p>
            <p>
                <label>Jenis Pengenalan Baru :</label>
                <s:select name="jeniskp">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>No Pengenalan Lama :</label>
                ${actionBean.pihak.noPengenalan}&nbsp;
            </p>
            <p>
                <label>No Pengenalan Baru :</label>
                <s:text name="nokp" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>--%>
            
            <%--<p>
                <label>Nombor Pengenalan Lama :</label>
                ${actionBean.pihak.noPengenalan}&nbsp;
            </p>
             <p>
                <label>Nombor Pengenalan Baru :</label>
                <s:text name="" />
            </p>--%>

           <%-- <p>
                <label>Alamat :</label>
                <s:text name="pihak.suratAlamat1" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat2" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat3" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat4" size="40"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="pihak.suratPoskod" size="40" maxlength="5"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="pihak.suratNegeri.kod" >
                    <s:option>Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>--%>
            <p>
                <s:hidden name="idPemohon" id="idPemohon" value="${actionBean.pemohon.idPemohon}"/>
                <label>&nbsp;</label>
                <s:button name="saveTukar" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset >
    </div>

</s:form>