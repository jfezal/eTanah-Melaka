<%-- 
    Document   : tambahsukudalamPihak
    Created on : Jun 7, 2011, 11:48:09 AM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">
    function save(event, f){
        if(confirm('Adakah anda pasti?')){

            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);

                setTimeout(function(){
                    self.opener.refreshPage();
                    self.close();
                }, 100);
            },'html');
            refreshPage();

        }
    }
</script>

<s:form beanclass="etanah.view.stripes.lelong.PihakPentingActionBean">
    <s:messages/>
    <s:errors/>&nbsp;

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Suku 
            </legend>
                <s:hidden name="namaPihak" value="${actionBean.pihak.idPihak}"/>
                <br>
                <label>Nama : </label>
                <font style="text-transform:uppercase;">${actionBean.pihak.nama}</font>

                <p>
                    <label>Nama Suku :</label>
                    <s:select id="suku" name="pihak.suku.kod" value="${actionBean.pihak.suku.kod}" style="width:200px;">
                        <s:option value="null">-Sila Pilih-</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodSuku}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p>
                    <label>Pecahan Suku / Lengkongan :</label>
                    <s:text id="" name="pihak.subSuku" onblur="this.value=this.value.toUpperCase();" style="width:200px;">
                    </s:text>
                </p>
                <p>
                    <label>Perut :</label>
                    <s:text id="" name="pihak.keturunan" onblur="this.value=this.value.toUpperCase();" style="width:200px;">
                    </s:text>
                </p>
                <p>
                    <label>Luak :</label>
                    <s:text id="" name="pihak.tempatSuku" onblur="this.value=this.value.toUpperCase();" style="width:200px;">
                    </s:text>
                </p>
                <p align="center"><label></label>
                    <br>
                    <s:button name="simpanSuku" value="Simpan" class="btn" onclick="save(this.name, this.form);" />
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
            </div>
            <br>
        </fieldset>
</s:form>
