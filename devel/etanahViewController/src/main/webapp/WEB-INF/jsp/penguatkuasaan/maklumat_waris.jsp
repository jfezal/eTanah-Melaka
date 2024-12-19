<%--
    Document   : maklumat_waris
    Created on : August 3, 2011
    Author     : siti.zainal

--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">

    function refreshPageWaris(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_waris?reload';
        $.get(url, function(data){$('#page_div').html(data);},'html');
    }

    
    function popup(z){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_waris?viewWarisDetail&idWaris='+z;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

    function tambahBaru(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_waris?popupWaris", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=600,scrollbars=yes");
    }

    function editWaris(i){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_waris?editWarisPopup&idWaris='+i;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }


    <%-- utk hapus waris--%>
        function removeWaris(i){
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_waris?deleteWaris&idWarisOks='+i;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');}
        }

  

    <%--view details of waris--%>
        function viewWaris(id){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_waris?viewWaris&idWaris='+id;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
        }

        function tambahWaris(i){
            window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_waris?popupWaris&idHakmilik="+i, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=600,scrollbars=yes");
        }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatWarisActionBean" id="folder_div">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Waris
            </legend>
            <div class="content" align="center">
                <c:if test="${actionBean.multipleHakmilik eq false}">
                    <div class="content" align="left">
                        <p>Klik butang tambah untuk masukkan Maklumat Waris</p>
                    </div>

                    <display:table class="tablecloth" name="${actionBean.listWaris}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Nama" class="rowCount"><a class="popup" onclick="popup(${line.idWarisOks})">${line.nama}</a></display:column>
                        <display:column title="Alamat">${line.alamat1}
                            <c:if test="${line.alamat2 ne null}"> , </c:if>
                            ${line.alamat2}
                            <c:if test="${line.alamat3 ne null}"> , </c:if>
                            ${line.alamat3}
                            <c:if test="${line.alamat4 ne null}"> , </c:if>
                            ${line.alamat4}
                            ${line.poskod}
                            ${line.negeri.nama}
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeWaris('${line.idWarisOks}');"/>
                            </div>
                        </display:column>

                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editWaris('${line.idWarisOks}')"/>
                            </div>
                        </display:column>


                    </display:table>
                    <table>
                        <tr>
                            <td align="right">
                                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>
                            </td>
                        </tr>
                    </table>
                    <br>
                </c:if>
                <c:if test="${actionBean.multipleHakmilik eq true}">
                    <span class=instr><em>Sila klik pada imej  <img src='${pageContext.request.contextPath}/images/tambah.png'/> untuk menambah waris.</em></span><br/>
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line" style="width:70%;">
                                <display:column title="Bil" style="width:3%;">${line_rowNum}</display:column>
                                <display:column title="No. Hakmilik" style="width:5%;">
                                    ${line.hakmilik.idHakmilik}
                                </display:column>
                                <display:column title="Maklumat Waris">
                                    <c:set value="1" var="count"/>
                            <table width="100%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>Nama</b></th>
                                    <th  width="1%" align="center"><b>Alamat</b></th>
                                    <th  width="5%" align="center" colspan="2"><b>Tindakan</b></th>
                                </tr>
                                <c:forEach items="${actionBean.listWaris}" var="senarai">
                                    <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                        <tr>
                                            <td width="5%">${count}</td>
                                            <td width="50%"><a class="popup" onclick="popup(${senarai.idWarisOks})">${senarai.nama}</a></td>
                                            <td width="50%">
                                                ${senarai.alamat1}
                                                <c:if test="${senarai.alamat2 ne null}"> , </c:if>
                                                ${senarai.alamat2}
                                                <c:if test="${senarai.alamat3 ne null}"> , </c:if>
                                                ${senarai.alamat3}
                                                <c:if test="${senarai.alamat4 ne null}"> , </c:if>
                                                ${senarai.alamat4}
                                                ${senarai.poskod}
                                                <font style="text-transform: uppercase">${senarai.negeri.nama}</font>

                                            </td>
                                            <td width="5%">
                                                <div align="center">
                                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Kemasikini rekod" onclick="editWaris('${senarai.idWarisOks}')"/>
                                                </div>
                                            </td>
                                            <td width="5%">
                                                <div align="center">
                                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Hapus rekod" onclick="removeWaris('${senarai.idWarisOks}');"/>
                                                </div>
                                            </td>


                                        </tr> 
                                    </c:if>


                                    <c:set value="${count+1}" var="count"/>
                                </c:forEach>
                            </table>
                        </display:column>
                        <display:column title="Tambah" style="width:3%;">
                            <div align="center">
                                <img alt='Tambah' id='addCaw' src='${pageContext.request.contextPath}/images/tambah.png' onmouseover="this.style.cursor='pointer';" onclick="tambahWaris('${line.hakmilik.idHakmilik}');"/>
                            </div>
                        </display:column>
                    </display:table>
                </c:if>

            </div>
        </fieldset>
    </div>
</s:form>