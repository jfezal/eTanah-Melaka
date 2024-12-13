<%--
    Document   : menuCapai
    Created on : June 22, 2011, 6:00:04 PM
    Author     : nurashidah.rosman
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function addOption(theSel, theText, theValue)
    {
        var newOpt = new Option(theText, theValue);
        var selLength = theSel.length;
        theSel.options[selLength] = newOpt;
    }

    function deleteOption(theSel, theIndex)
    {
        var selLength = theSel.length;
        if(selLength>0)
        {
            theSel.options[theIndex] = null;
        }
    }

    function moveOptions(theSelFrom, theSelTo)
    {

        var selLength = theSelFrom.length;
        var selectedText = new Array();
        var selectedValues = new Array();
        var selectedCount = 0;

        var i;

        for(i=selLength-1; i>=0; i--)
        {

            if(theSelFrom.options[i].selected)
            {
                
                selectedText[selectedCount] = theSelFrom.options[i].text;
                selectedValues[selectedCount] = theSelFrom.options[i].value;

                deleteOption(theSelFrom, i);
                selectedCount++;
            }
        }

        for(i=selectedCount-1; i>=0; i--)
        {
            addOption(theSelTo, selectedText[i], selectedValues[i]);
            
            var table = document.getElementById("tbl");
            var rowcount = table.rows.length;
            var row = table.insertRow(rowcount);
            document.getElementById("recordCount").value =rowcount;

            var cell1 = row.insertCell(0);
            var bil = document.createTextNode(rowcount);
            cell1.appendChild(bil);

            var cell2 = row.insertCell(1);
            var e1 = document.createElement("INPUT");
            e1.setAttribute("type","text");
            e1.setAttribute("name","idPeranan"+(rowcount-1));
            e1.setAttribute("id","idPeranan" +(rowcount-1));
            e1.setAttribute("value",selectedValues[i]);
            cell2.appendChild(e1);
        }
    }

    function hapusOptions(theSelFrom, theSelTo)
    {

        var selLength = theSelFrom.length;
        var selectedText = new Array();
        var selectedValues = new Array();
        var selectedCount = 0;

        var i;

        // Find the selected Options in reverse order
        // and delete them from the 'from' Select.
        for(i=selLength-1; i>=0; i--)
        {
            if(theSelFrom.options[i].selected)
            {
    <%--alert(i);--%>
                    selectedText[selectedCount] = theSelFrom.options[i].text;
                    selectedValues[selectedCount] = theSelFrom.options[i].value;

                    document.getElementById('tbl').deleteRow(i+1);
    <%--alert(i+1);--%>
                    deleteOption(theSelFrom, i);
                
    <%--var table = document.getElementById('tbl');
    var rowCount = table.rows.length;

                document.getElementById("recordCount").value =rowCount-1;
            
                if(rowCount > 1){
                    for(var j=1;j<rowCount;j++){
                        table.rows[j].cells[j].childNodes[0].data= j;
                    }
               
                }
    --%>

                    selectedCount++;
                }
            }

            // Add the selected text/values in reverse order.
            // This will add the Options to the 'to' Select
            // in the same order as they were in the 'from' Select.
            for(i=selectedCount-1; i>=0; i--)
            {
                addOption(theSelTo, selectedText[i], selectedValues[i]);
            }
        }

        function validateForm(){

            if(document.menuCapaiForm.menu.value=="")
            {
                alert('Sila pilih Menu Item.');
                $('#menu').focus();
                return false;
            }

        }

        function test(f) {
            $(f).clearForm();
        }

        function findMenu(frm, id){
            if(id != ""){

                var url = '${pageContext.request.contextPath}/uam/menu_capai?findIdMenu&id='+id;
                frm.action = url;
                frm.submit();
            }
        }

        function removePerananMenu(frm, val) {
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var menu = $('#menu').val();
                var url = '${pageContext.request.contextPath}/uam/menu_capai?deletePerananMenu&idCapai='+val+'&idMenu='+menu;
                frm.action = url;
                frm.submit();
            }
        }


</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>

<s:form beanclass="etanah.view.uam.MenuCapaianActionBean" name="menuCapaiForm">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Capaian Menu
            </legend>
            <p>
                <label>Senarai Menu</label>
                <s:select name="menu" value="${actionBean.menu}" id="menu" onchange="findMenu(this.form, this.value)">
                    <s:option value=""> Sila Pilih </s:option>
                    <c:forEach items="${actionBean.senaraiMenu}" var="line">
                        <s:option value="${line.idMenu}">${line.tajuk} - (${line.idMenu})</s:option>
                    </c:forEach>

                </s:select>
            </p>
            <div class="content" align="center">
                <display:table name="${actionBean.senaraiKPMenu}" id="line" class="tablecloth" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/uam/menu_capai">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column property="peranan.nama" title="Peranan" style="vertical-align:baseline" ></display:column>
                    <%--<display:column property="idCapaian" title="id Capai" style="vertical-align:baseline" ></display:column>--%>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePerananMenu(document.forms.menuCapaiForm, '${line.idCapaian}')"/>
                            <%--id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePerananMenu(document.forms.menuCapaiForm, '${line.peranan.kod}', '${line.menu.idMenu}')"/>--%>
                        </div>
                    </display:column>

                </display:table>
            </div><br>
            <p>
                <label>Kod Peranan :</label>
                <font size="2" color="Red">
                    <strong>Sila pilih Kod Peranan.</strong>
                </font>
            </p><br>
            <div align="center">
                <table align="center">
                    <tr>
                        <td>
                            <s:select name="senaraiKP" id="senaraiKP" multiple="multiple" size="all" style="height: 300px; width: 400px;" >
                                <%--<s:option value=""> Sila Pilih </s:option>--%>
                                <c:forEach items="${actionBean.senaraiKodPeranan}" var="line">
                                    <s:option value="${line.kod}">${line.nama} - (${line.kod})</s:option>
                                </c:forEach>
                            </s:select>
                        </td>
                        <td>
                            <%--<s:button name="add" value="Tambah >>" class="btn" onclick="addOptions(this.form.senaraiKP, this.form.peranan);"/><br><br>
                            <s:button name="remove" value="Hapus <<" class="btn" onclick="removeOptionSelected();"/><br><br>--%>
                            <s:button name="add" value="Tambah >>" class="btn" onclick="moveOptions(this.form.senaraiKP, this.form.peranan);"/><br><br>
                            <s:button name="remove" value="Hapus <<" class="btn" onclick="hapusOptions(this.form.peranan, this.form.senaraiKP);"/><br><br>
                            <br><br><br>
                            <s:submit name="simpan" id="simpan" value="Simpan" class="btn" onclick="return validateForm();"/>
                        </td>
                        <td>
                            <%--<c:set value="0" var="i"/>--%>
                            <s:select name="peranan" id="peranan" multiple="multiple" size="all" style='height: 300px; width: 400px;'>

                                <%--<c:forEach items="${actionBean.senaraiKPMenu}" var="line">
                                    <s:option value="${line.peranan.kod}">${line.peranan.nama} - (${line.peranan.kod})</s:option>
                                    <c:set var="i" value="${i+1}"/>
                                </c:forEach>--%>
                            </s:select>
                        </td>

                    </tr>
                </table>
                <div id="divIdPeranan" style="visibility: hidden; display: none">
                    <input type="text" name="recordCount" id="recordCount"/>
                    <table  width="60%" id="tbl" class="tablecloth" align="center">
                        <c:set value="0" var="i"/>
                        <tr style="font-weight:bold">
                            <%--  <td><c:out value="${i+1}"/></td>
                              <td><s:text name="idPeranan${i}" id="idPeranan${i}"/></td>--%>
                        </tr>
                        <%--<s:hidden name="idP${i}" id="idP${i}" value="${line.idOperasiPenguatkuasaanPasukan}" />--%>
                        <c:set var="i" value="${i+1}" />

                    </table>
                </div>
            </div><br>
        </fieldset>
    </div>

</s:form>