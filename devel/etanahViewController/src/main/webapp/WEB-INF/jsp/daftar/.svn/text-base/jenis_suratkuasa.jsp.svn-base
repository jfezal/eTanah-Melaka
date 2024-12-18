<%--
    Document   : jenis_suratkuasa
    Created on : Dec 15, 2009, 5:27:12 PM
    Author     : mohd.fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>

<script type="text/javascript">
    $(document).ready(function() {
        if(document.form1.pilihSemua.checked == true){

            var x=  document.getElementsByName("check")
            var xy = document.getElementById("pilihSemua")
            checkUncheckAll(xy, x)
        }
    });

    function saveSemua()
    {
        var x=  document.getElementsByName("check")
        var xy = document.getElementById("pilihSemua")
        if(document.form1.pilihSemua.checked == true){
            var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?semuaHakmilik';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        else
        {
            var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?untickSemuaHakmilik';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        checkUncheckAll(xy, x)
    }

    function checkUncheckAll(checkAllState, cbGroup)
    { if(document.form1.pilihSemua.checked == true){
            if(cbGroup.length > 0)
            {
                for (i = 0; i < cbGroup.length; i++)
                {
                    cbGroup[i].checked = checkAllState.checked;
                    cbGroup[i].disabled = true;
                }
            }
            else
            {
                cbGroup.checked = checkAllState.checked;
            }
        }
        else
        {
            for (i = 0; i < cbGroup.length; i++)
            {
                cbGroup[i].checked = checkAllState.checked;
                cbGroup[i].disabled = false;
            }
        }
    }

    function addWakilHakmilik(idH, no)
    {
        var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?simpanWakilhakmilik&idH='
            +idH;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function removeSingle(idH)
    {
        var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?deleteSingle&idH='
            +idH;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }


</script>


<s:form name="form1" beanclass="etanah.view.daftar.SuratKuasaWakilActionBean">

    <s:messages />
    <s:errors />


    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Senarai Hakmilik Diwakilkan
            </legend>
            <br/>
            <p>
                <label>Hakmilik Terlibat :</label>
            <table class="tablecloth">
                <tr>
                    <th>Bil</th>
                    <th>ID Hakmilik</th>
                    <th>Pilih</th>
                </tr>
                <c:forEach var="i" begin="1" end="${actionBean.size}" >
                    <tr id="row${i}">
                        <td align=right>
                            ${i}
                        </td><td align=left>
                            ${actionBean.hpB[i-1].hakmilik.idHakmilik}
                        </td>
                        <td>
                            <s:checkbox name="check" value="${i}" onclick="addWakilHakmilik('${actionBean.hpB[i-1].hakmilik.idHakmilik}','${i}')"/>

                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2" align="center">Semua Hakmilik</td>
                    <td>
                        <s:checkbox name="wakilPihak.untukSemuaHakmilik" id="pilihSemua" value="Y" onclick="saveSemua()"/>
                    </td>
                </tr>
            </table>

            <br/>
            <p align="center"><label></label>
                <display:table class="tablecloth" name="${actionBean.wHakmilik}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="Id Hakmilik" class="nama"/>
                    <display:column title="Padam">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.wHakmilik[line_rowNum-1].hakmilik.idHakmilik}');" />
                    </div>
                </display:column>
            </display:table>
            </p>
            <br/>
            <br/>
        </fieldset>


    </div>


    <br>

</s:form>
