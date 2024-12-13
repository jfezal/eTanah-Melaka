<%--
    Document   : maklumat_pemilik_petak_berhutang
    Created on : Apr 7, 2011, 12:24:04 AM
    Author     : zadhirul.farihim
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function savePemohon(event, f){
        return false;
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.get(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
            //   refreshPage();
        },'html');
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.strata.PelaksanaWaranActionBean">

    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tambah Pelaksana Waran</legend>

            <p>
                <label title="Nama" >Nama :</label><s:text name="nama" size="100"/>
            </p>
                        <p>
                <label>Jenis Pengenalan :</label>
                <s:select name="jenisKP" >
                    <s:option>Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label title="No Pengenalan" >No Pengenalan :</label><s:text name="noPengenalan"/>
            </p>

            <p>
                <label title="Alamat">Alamat :</label><s:text name="alamat1"/>
            </p>
            <p>
                <label title="Alamat">&nbsp;</label><s:text name="alamat2"/>
            </p>
            <p>
                <label title="Alamat">&nbsp;</label><s:text name="alamat3"/>
            </p>
            <p>
                <label title="Alamat">&nbsp;</label><s:text name="alamat4"/>
            </p>
            <p>
                <label title="Poskod">Poskod :</label><s:text name="poskod"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="negeri" >
                    <s:option>Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label title="Pekerjaan">Pekerjaan :</label><s:text name="kerja"/>

            </p>
            <p >
                <label>&nbsp;</label> <s:button name="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form);"/>
            </p>
            <br>
            <br>
        </fieldset>
    </div>
</s:form> 
