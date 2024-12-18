<%--
    Document   : ulasan_jabatan_teknikal
    Created on : Dec 29, 2009, 12:34:35 PM
    Author     : muhammad.amin
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">

    function addRow(){

        var rowNo = $('table#line1 tr').length;
        $('table#line1 > tbody').append("<tr id='x"+rowNo+"' class='x'>\n\
                <td class='rowNo'>"+rowNo+"</td>\n\
                <td><select name=jabatanArray style=width:400px>"+getOption()+"</select></td><td><textarea name=ulasanArray rows=3 cols=40/></td>\n\
                </tr>");
    }
    function getOption(){
        var option='';
        var senaraiJabatan = document.getElementById('jabatan');
        for (var i = 0; i < senaraiJabatan.options.length; ++i){
            option = option + '<option value='+senaraiJabatan.options[i].value+'>'+senaraiJabatan.options[i].text+'</option>';
        }
        return option;
    }

</script>

<s:form beanclass="etanah.view.stripes.common.UlasanJabatanTeknikalActionBean">
    <s:messages/>
    <s:hidden name="permohonan.idPermohonan" />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Ulasan Jabatan Teknikal</legend>
            <c:if test="${edit}">
                <div class="content" align="center">
                    <display:table name="${actionBean.listUlasan2}" id="line1" class="tablecloth">
                        <display:column title="No">
                            ${line1_rowNum}
                        </display:column>
                        <display:column title="Jabatan">
                            <s:select name="jabatanArray" id="jabatan" style="width:400px">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodAgensi}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Ulasan"> <s:textarea value="${line1.ulasan}" name="ulasanArray" rows="3" cols="40"/></display:column>
                    </display:table>
                    <s:button class="btn" value="Tambah" name="pilih" id="pilih" onclick="addRow();"/>&nbsp;
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </div>
            </c:if>
            <c:if test="${!edit}">
                <div class="content" align="center">
                    <display:table name="${actionBean.listUlasan2}" id="line1" class="tablecloth">
                        <display:column title="No">
                            ${line1_rowNum}
                        </display:column>
                        <display:column title="Jabatan" property="agensi.nama"/>
                        <display:column title="Ulasan" property="ulasan"/>
                    </display:table>
                </div>
            </c:if>
        </fieldset >
    </div>
</s:form>