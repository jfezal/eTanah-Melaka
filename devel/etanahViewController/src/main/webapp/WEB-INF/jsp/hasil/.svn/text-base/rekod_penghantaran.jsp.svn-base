<%--
    Document   : rekod_penghantaran
    Created on : Dec 26, 2009, 2:00:17 AM
    Author     : nurfaizati
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function blankValidation(){
        var result = true;
        if(($('#noDasar').val() == null || $('#noDasar').val() == "") && ($('#status').val() == null || $('#status').val() == "")){
            alert("Sila isi No. Rujukan Dasar dan Pilih Status terlebih dahulu.");
            $('#noDasar').focus();
            result = false;
        }else if($('#noDasar').val() == null || $('#noDasar').val() == ""){
            alert("Sila isi No. Rujukan Dasar terlebih dahulu.");
            $('#noDasar').focus();
            result = false;
        }else if($('#status').val() == null || $('#status').val() == ""){
            alert("Sila Pilih Status terlebih dahulu.");
            $('#status').focus();
            result = false;
        }
        return result;
    }

    function edit(id){
        window.open("${pageContext.request.contextPath}/hasil/rekod_penghantaran?popup&idHakmilik="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

      function popup(id1,id2){
        alert(id1);
          alert(id2);
                  <%--var queryString = $(f).formSerialize()--%>
        window.open("${pageContext.request.contextPath}/hasil/rekod_penghantaran?popup1&idHakmilik="+id1+"&kodStatus="+id2, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600,scrollbars=1");
    }

    function dateValidation(id,value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("");
        }
    }

</script>

<s:form beanclass="etanah.view.stripes.hasil.RekodPenghantaranActionBean" id="rekod_penghantaran">
    <s:hidden name="kodStatus2"/>
    <s:messages/>
    <s:errors/>&nbsp;
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kemasukan Rekod Penghantaran
            </legend>
            <p>
                <label><font color="red">*</font>No. Rujukan Dasar :</label>
                <s:text name="noDasar" id="noDasar" size="35" maxlength="30" onblur="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><em>*</em>Pilih Status :</label>
                <s:select id="status" name="kodStatus" style="width:235px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNotis}" label="nama" value="kod"   />
                </s:select>
            </p>
            <table width="100%">
                <tr>
                    <td align="right">
                        <s:submit class="btn" onclick="return blankValidation();" name="search" value="Cari"/>&nbsp;
                        <s:button class="btn" name="reset" value="Isi Semula" onclick="clearText('rekod_penghantaran');"/>
                    </td>
                </tr>
            </table>
        </fieldset>
        <br>
        <c:if test="${actionBean.visible}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Senarai Rekod Penghantaran
                    </legend>
                    <p class=instr>
                        *<font color="red">Nota</font>

                    </p>
                    <p class=instr>
                        <font color="red">H:</font> Tarikh Hantar
                        <font color="red">T:</font> Tarikh Terima
                    </p>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="content" align="center">

                        <display:table class="tablecloth" name="${actionBean.senaraiSementara}"
                                       pagesize="12" cellpadding="0" cellspacing="0" requestURI="/hasil/rekod_penghantaran" id="line">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <%--<display:column property="notis.hakmilik.akaunCukai.pemegang.nama" title="Nama" class="${line_rowNum}" />--%>
                            <display:column property="notis.hakmilik.akaunCukai.noAkaun" title="Nombor Akaun" class="${line_rowNum}"/>
                            <%--<display:column property="notis.hakmilik.idHakmilik" title="ID Hakmilik" class="${line_rowNum}"/>--%>
                            <display:column title="ID Hakmilik"><a href="#" onclick="popup('${line.notis.hakmilik.idHakmilik}','${actionBean.kodStatus}');return false;">${line.notis.hakmilik.idHakmilik}</a></display:column>
                            <display:column title="Nama Pemilik" style="width:200px">
                                    <c:set value="1" var="count"/>
                            <c:forEach items="${line.notis.hakmilik.senaraiPihakBerkepentingan}" var="senarai" >
                                <c:out value="${count}"/>) <c:out value="${senarai.pihak.nama}"/>
                                <c:set value="${count +1}" var="count"/><br>
                            </c:forEach>
                            </display:column>
                            <display:column title="Status Penyampaian" class="${line_rowNum}">
                                <c:set value="1" var="counts"/>
                                <c:forEach items="${line.notis.hakmilik.senaraiPihakBerkepentingan}">
                                <c:out value="${counts}"/>)
                                <s:select name="kodStatus2[${line_rowNum-1}]"><font color="red">*</font>
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod" sort="nama" id="kodStatus2" />
                                </s:select><c:set value="${counts +1}" var="counts"/><br></c:forEach>
                            </display:column>
                            <%--<c:if test="${actionBean.caraPenghantaran}">--%>
                                <display:column title="Cara Penghantaran" class="${line_rowNum}" style="width:170px;"><font color="red">*</font>
                                    <s:select name="kodCaraHantar[${line_rowNum-1}]" >
                                        <s:option value="">--Sila Pilih--</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" id="kodCaraHantar" value="kod" sort="nama" />
                                    </s:select>
                                </display:column>
                            <%--</c:if>--%>
                            <display:column title="Tarikh " class="${line_rowNum}" style="width:150px;">
                                <p>H :<s:text id="tarikhDihantar${line_rowNum-1}" class="datepicker" name="tarikhDihantar[${line_rowNum-1}]" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id,this.value)" style="width:100px;"/></p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                                <p>T :<s:text id="tarikhTerima${line_rowNum-1}" class="datepicker" name="tarikhTerima[${line_rowNum-1}]" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id,this.value)" style="width:100px;"/></p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                            </display:column>
                            <display:column  title="Catatan"  class="${line_rowNum}" >
                                <s:textarea name="catatanTerima[${line_rowNum-1}]" rows="5" cols="25" onblur="this.value=this.value.toUpperCase();" />

                            </display:column>
                        </display:table>

                    </div>
                    <p>
                        <label>
                            <div class="instr" align="center">
                            </div>
                        </label>
                    </p>
                    <p align="right">
                        <s:submit class="btn"  name="save" value="Simpan" id="btn" disabled="${actionBean.btn}" onclick="save(this.name, this.form);"/>
                    </p>
                    <%--<table border="0" width="100%">
                        <tr>
                            <td align="right">
                                <s:submit class="btn"  name="save" value="Simpan" id="btn" disabled="${actionBean.btn}" onclick="save(this.name, this.form);"/>
                            </td>
                        </tr>
                    </table>--%>
                </fieldset>
            </div>
        </c:if>
    </div>
</s:form>
