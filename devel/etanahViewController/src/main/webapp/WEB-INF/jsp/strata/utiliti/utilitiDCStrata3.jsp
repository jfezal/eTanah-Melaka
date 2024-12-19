<%--
    Document   : DataCollection
    Created on : July 02, 2014, 10:31:32 PM
    Author     : Mudd
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }

    function masuk(){
    <%--var index=document.getElementById('index').value;--%>
            <%--opener.document.getElementById('kod').value = $("#selectedKod").val();--%>
            opener.document.getElementById('nama').value = $("#nama").val();
            self.close();
        }

        function selectRadio(obj){

    <%--document.getElementById("selectedKod").value=obj.id;--%>
            document.getElementById("selectedNama").value=obj.value;

        }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:errors/>
<s:messages/>
<div class="subtitle">
    <s:form beanclass="etanah.view.strata.utiliti.UtilityDataCollectionActionBean">
        <fieldset class="aras1">
            <legend>
                Carian Perbadanan Pengurusan
            </legend>
            <%--<s:hidden id="selectedKod" name="selectedKod" />--%>
            <s:hidden id="selectedNama" name="selectedNama" />

            <%--<p>
                <s:hidden name="index" id="index" />
            </p>--%>
            <%--<p>

                <label>Id Pihak :</label>
                <s:text name="kodSyaratNyata" id="kodSyaratNyata"/>
            </p>--%>
            <p>

                <label>Nama :</label>
                <s:text name="nama" id="nama"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="StrbdnUrus" value="Cari" class="btn"/>
                <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
            </p>
        </fieldset>
    </div>
    <br>

    <div class="subtitle">

        <%-- <c:if test="${actionBean.kodSyaratNyata != null}">--%>

        <fieldset class="aras1">
            <legend></legend>
            <p>
                <display:table style="width:100%" class="tablecloth" name="${actionBean.senaraiPihak}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/utiliti/datacollection" id="line">
                    <display:column> <s:radio name="radio_" id="${line.nama}" value="${line.nama}"   onclick="javascript:selectRadio(this)"/></display:column>
                    <%--<display:column title="Kod Syarat Nyata" property="kod"/>--%>
                    <display:column title="Nama Kod Syarat Nyata" property="nama"/>
                </display:table>
            </p>
            <c:if test="${fn:length(actionBean.senaraiPihak) > 0}" >
                <p><label>&nbsp;</label>
                    <s:button name="simpanPihak" value="Simpan" id="simpanPihak" class="btn" onclick="javascript:masuk();"/>
                </p>
            </c:if>
        </fieldset>

    </s:form>
</div>

