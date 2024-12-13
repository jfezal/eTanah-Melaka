<%-- 
    Document   : AllUser
    Created on : Apr 12, 2010, 3:41:52 PM
    Author     : solahuddin/wan.fairul
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function removePengguna(frm, val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/uam/all_user?deletePengguna&idPengguna='+val;
            /*$.get(url,
            function(data){
                $('#all').html(data);
            },'html');*/
            frm.action = url;
            frm.submit();
        }
    }

    function editPengguna(frm, val) {
        var url = '${pageContext.request.contextPath}/uam/new_update?searchUser&idPengguna='+val;
        frm.action = url;
        frm.submit();
    }
            function detailPengguna(pguna){
//        alert(pguna);
         var url = '${pageContext.request.contextPath}/uam/pengguna_Details?viewPenggunaDetails&idPengguna=' + pguna;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600,scrollbars=yes");
    }

    function popup(url)
    {
        params  = 'width=900';
        params += ', height=900';
        params += ', top=200, left=100';
        params += ', directories=no';
        params += ', fullscreen=no';
        params += ', location=no';
        params += ', menubar=yes';
        params += ', resizable=yes';
        params += ', scrollbars=yes';
        params += ', status=yes';
        params += ', toolbar=yes';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }
        function doSomething2(jabatan,f){
//        var jabatan = f.elements['pguna.kodJabatan.kod'].value ;
//   alert(jabatan);
        var q = $(f).serialize();
        var url = '${pageContext.request.contextPath}/uam/all_user?perananByJabatan&jabatan='+jabatan;
        window.location = url+q;
    }
</script>
<div id="all">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
    <s:form beanclass="etanah.view.uam.AllUserActionBean" name="userForm" id="user_uam">
        <s:messages/>
        <s:errors/>

        <div class="subtitle displaytag">
            <fieldset class="aras1">
                <legend>Carian Pengguna</legend>
                <p>
                    <font color="red">PERINGATAN :</font>  Sila Masukkan Maklumat Berikut.
                </p>
                <p>
                    <label>ID Pengguna :</label>
                    <s:text name="pguna.idPengguna" size="31"/>
                </p>
                <p>
                    <c:if test="${!actionBean.melaka}"><label>Cawangan :</label></c:if>                   
                    <c:if test="${actionBean.melaka}"><label>Jabatan :</label></c:if>

                    <s:select name="kodCawangan" style="width:250px">
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" value="kod" label="name"/>
                    </s:select>
                </p>
                <p> <c:if test="${!actionBean.melaka}"><label>Jabatan :</label></c:if>                   
                    <c:if test="${actionBean.melaka}"><label>Unit :</label></c:if>

                    <s:select name="kod_jab" style="width:250px;" onchange="doSomething2(this.value,this.form);">
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodJabatan}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p>
                    <label>Peranan :</label>
                    <s:select name="perananUtama" style="width:250px">
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listPP}" value="kod" label="nama"/>
                    </s:select>
                </p>
                <p><label>Status :</label>
                    <s:select name="status" style="width:250px;">
                        <s:option value="" >Sila Pilih</s:option>
                        <s:option value="A" >Aktif</s:option>
                        <s:option value="X" >Tidak Aktif</s:option>
                    </s:select>
                </p>
                <div align="right">
                    <s:submit name="searchUser" value="Cari" class="btn" />
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="clearText('user_uam');"/>
                </div>
            </fieldset >
            <c:if test="${actionBean.flag}">
                <fieldset class="aras1">
                    <legend>
                        Senarai Pengguna
                    </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.userList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/uam/all_user">
                            <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                             <display:column  title="ID Pengguna" sortable="true" style="vertical-align:baseline"><a style="cursor: pointer" onclick="detailPengguna('${line.pengguna.idPengguna}')">  ${line.pengguna.idPengguna}</a></display:column>
                       
                            <display:column property="pengguna.nama" title="Nama" style="vertical-align:baseline"/>
                            <display:column property="pengguna.tarikhAkhirLogin" title="Tarikh Akhir Login" style="vertical-align:baseline"format="{0,date,dd/MM/yyyy - hh:mm:ss a}"/>
                            <display:column property="tarikhAkhirAktiviti" title="Tarikh Akhir Aktiviti" style="vertical-align:baseline" format="{0,date,dd/MM/yyyy - hh:mm:ss a}"/>
                          <%--  <display:column property="jawatan" title="Jawatan" style="vertical-align:baseline"/>--%>
                            <display:column title="Status Online" style="vertical-align:baseline">
                                <c:if test="${line.statusOnline eq false}"><font color="red">OFFLINE</font></c:if>
                                <c:if test="${line.statusOnline eq true}"><font color="green">ONLINE</font></c:if>
                            </display:column>
                           
                            <%--<display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="editPengguna(document.forms.userForm, '${line.pengguna.idPengguna}')" onmouseover="this.style.cursor='pointer';">
                                </div>
                            </display:column>--%>
                            <%--<display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="removePengguna(document.forms.userForm, '${line.idPengguna}')" onmouseover="this.style.cursor='pointer';">
                                </div>
                            </display:column>--%>
                        </display:table>
                    </div>
                </fieldset>
            </c:if>
            <br>
            <fieldset class="aras1">
                <legend>
                    Senarai Laporan
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <div align="center">
                    <table class="tablecloth">
                        <tr style="width: 100%">
                            <th>Bil</th>
                            <th>Laporan</th>
                            <th>Papar</th>

                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.senaraiCarianPengguna}" var="report">
                            <tr>
                                <td>${count}
                                </td>
                                <td>${report}
                                </td>
                                <td><div align="center">
                                        <p align="center">
                                            <img title="Klik Untuk Papar" alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="popup('${pageContext.request.contextPath}/uam/all_user/requestParam?namaReport=${actionBean.senaraiCarianPenggunaRN[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                        </p>
                                    </div>
                                </td>
                            <c:set value="${count +1}" var="count"/>
                            </tr>
                            <c:set value="${count +1}" var="count"/>
                        </c:forEach>
                        </tr>
                    </table>
                </div>
                <br>
            </fieldset>        
        </div>
    </s:form>
</div>