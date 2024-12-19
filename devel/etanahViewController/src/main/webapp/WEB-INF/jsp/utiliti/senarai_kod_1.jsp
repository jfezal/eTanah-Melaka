<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="list" />

<script type="text/javascript">
    function tutup(){
        self.close() ;
        opener.confirmRefresh() ;
    }

<%--    function checkingKod(kod){
        alert(kod);
        $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckHakmilik&idHakmilik=" + inputTxt+"&type="+type,
                    function(data){
                        var dataLength= data.length;
                        if((data == '1')||(data == '7')||(dataLength>2)){
                            $("#msg" + inputTxt).html("OK");
                            $('#next').removeAttr("disabled");
                            $('#next').removeAttr("disabled", true);
                            if(type == 'hakmilik')
                                $("#hakmilik").val(inputTxt.toUpperCase());
                        }
                        });
    }--%>
</script>
<script language="javascript" type="text/javascript">
	$(document).ready(function() {
            var m = ${actionBean.addNew}
            var flag = ${actionBean.flagSave}
            if(m){
                $('#edit').hide();
                $('#add').show();
            }else{
                $('#edit').show();
                $('#add').hide();
            }
            if(flag){
                self.close() ;
                opener.confirmRefresh() ;
            }
        });
</script>
<s:form beanclass="etanah.view.utility.ListKodActionBean" >
    <s:hidden name="nameTable" id="nameTable"/>

    <fieldset class="aras1">
        <c:if test="${actionBean.nameTable eq 'KOD_SEKATAN'}">
            <legend>Kemaskini Kod Sekatan</legend>
            <p>
                <label>Kod :</label>
                <%--${actionBean.sekatan.kod}--%>
                <s:text name="sekatan.kod" readonly="true"/>
            </p>

            <p>
                <label>Kod Cawangan :</label>
                <s:select name="sekatan.cawangan.kod" style="width:246px">
                    <s:option value="">Sila Pilih</s:option>
                    <c:forEach items="${list.senaraiKodCawangan}" var="i">
                        <s:option value="${i.kod}">${i.kod} - ${i.name}</s:option>
                    </c:forEach>
                </s:select>
            </p>

            <p>
                <label>Sekatan :</label>
                <s:textarea name="sekatan.sekatan" rows="8" cols="50"/>
            </p>

            <p>
                <label>Aktif :</label>
                <s:select name="sekatan.aktif" style="width:246px">
                    <s:option value='Y'>Ya</s:option>
                    <s:option value='T'>Tidak</s:option>
                </s:select>
            </p>

            <p>
                <label>Kod Sekatan :</label>
                <s:text name="sekatan.kodSekatan"/>
            </p>

            <c:if test="${actionBean.sekatan.infoAudit ne null}">
                <p>
                    <label>Dimasuk Oleh :</label>
                    ${actionBean.sekatan.infoAudit.dimasukOleh.idPengguna}
                    <%--<s:text name="sekatan.infoAudit.dimasukOleh.idPengguna" readonly="true"/>--%>
                </p>

                <p>
                    <label>Tarikh Masuk :</label>
                    <fmt:formatDate value="${actionBean.sekatan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm a"/>
                    <%--<s:text name="sekatan.infoAudit.tarikhMasuk" readonly="true" style="pattern=dd/MM/yyyy hh:mm a"/>--%>
                </p>
            </c:if>
        </c:if>

        <c:if test="${actionBean.nameTable eq 'KOD_SYARAT_NYATA'}">
            <legend>Kemaskini Kod Syarat Nyata</legend>
            <p>
                <label>Kod :</label>
                <s:text name="syarat.kod"/>
            </p>

            <p>
                <label>Kod Kategori Tanah :</label>
                <s:select name="syarat.kategoriTanah.kod" style="width:246px">
                    <s:option value="">Sila Pilih</s:option>
                    <c:forEach items="${list.senaraiKodKategoriTanah}" var="i">
                        <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                    </c:forEach>
                </s:select>
            </p>

            <p>
                <label>Kod Cawangan :</label>
                <s:select name="syarat.cawangan.kod" style="width:246px">
                    <s:option value="">Sila Pilih</s:option>
                    <c:forEach items="${list.senaraiKodCawangan}" var="i">
                        <s:option value="${i.kod}">${i.kod} - ${i.name}</s:option>
                    </c:forEach>
                </s:select>
            </p>

            <p>
                <label>Syarat :</label>
                <s:textarea name="syarat.syarat" rows="8" cols="50"/>
            </p>

            <p>
                <label>Aktif :</label>
                <s:select name="syarat.aktif" style="width:246px">
                    <s:option value='Y'>Ya</s:option>
                    <s:option value='T'>Tidak</s:option>
                </s:select>
            </p>

            <p>
                <label>Kod Syarat :</label>
                <s:text name="syarat.kodSyarat"/>
            </p>

            <c:if test="${actionBean.syarat.infoAudit ne null}">
                <p>
                    <label>Dimasuk Oleh :</label>
                    ${actionBean.syarat.infoAudit.dimasukOleh.idPengguna}
                    <%--<s:text name="syarat.infoAudit.dimasukOleh.idPengguna" readonly="true"/>--%>
                </p>

                <p>
                    <label>Tarikh Masuk :</label>
                    <fmt:formatDate value="${actionBean.syarat.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm a"/>
                    <%--<s:text name="syarat.infoAudit.tarikhMasuk" readonly="true" style="pattern=dd/MM/yyyy hh:mm a"/>--%>
                </p>
            </c:if>
        </c:if>

        <table border="0" bgcolor="green" width="100%">
            <tr>
                <td align="right">
                    <s:submit name="saveEditData" value="Simpan E" class="btn" id="edit"/>
                    <%--<s:submit name="saveEditData" value="Simpan E" class="btn" id="edit"/>--%>
                    <s:button name=" " value="Simpan A" class="btn" id="add"/>
                    <s:button name=" " value="Tutup" class="btn" id="close" onclick="self.close()"/>
                </td>
            </tr>
        </table>
    </fieldset>

</s:form>
