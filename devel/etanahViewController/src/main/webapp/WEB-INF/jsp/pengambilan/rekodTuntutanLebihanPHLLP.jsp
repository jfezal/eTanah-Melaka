<%-- 
    Document   : rekodTuntutanLebihan
    Created on : 18-Oct-2010, 11:42:00
    Author     : nordiyana
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function removeSingle(id)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/rekodTuntut?deleteSingle&id='
                +id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    <%-- alert(ialertd);--%>
        }
        function tambahBaru(){
            window.open("${pageContext.request.contextPath}/pengambilan/rekodTuntut?hakMilikPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function refreshPageHakmilik(){
            var url = '${pageContext.request.contextPath}/pengambilan/rekodTuntut?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        $(document).ready( function() {


            var len = $(".daerah").length;

            for (var i=0; i<=len; i++){
                $('.hakmilik'+i).click( function() {
                    window.open("${pageContext.request.contextPath}/pengambilan/rekodTuntut?popup&idHakmilik="+$(this).text(), "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
                });
            }
        });

        function validateLuas(idVar,rowNo){

            var str = idVar.value;
            var luasTerlibat = parseInt(idVar.value);
            var luas = parseInt(document.getElementById("luas"+rowNo).value);

            if(luasTerlibat > luas){
                alert("Luas Diambil must less than Luas");
                idVar.value = str.substring(0,str.length-1);
                idVar.focus();
                return true;
            }
        }

        function validateKodUnitLuas(idVar,rowNo){
    <%--alert(idVar.value);--%>
            if(idVar.value == 'M'){
                var unitLuasDiambil = "Meter Persegi";
                alert(unitLuasDiambil);
            }
            if(idVar.value == 'H'){
                var unitLuasDiambil = "Hektar";
                alert(unitLuasDiambil);
            }

            var unitLuas = document.getElementById("unitLuas"+rowNo).value;
            alert(unitLuas);

        }

        function save(event, f){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);

            },'html');

        }

        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

        function removeNonNumeric( strString )
        {
            var strValidCharacters = "1234567890";
            var strReturn = "";
            var strBuffer = "";
            var intIndex = 0;
            // Loop through the string
            for( intIndex = 0; intIndex < strString.length; intIndex++ )
            {
                strBuffer = strString.substr( intIndex, 1 );
                // Is this a number
                if( strValidCharacters.indexOf( strBuffer ) > -1 )
                {
                    strReturn += strBuffer;
                }
            }
            return strReturn;
        }
        function ajaxLink(link, update) {
    <%--  alert(update);
      alert(link);
      $.get(link, function(data) {
          $(update).html(data);
          $(update).show();
      });
      return false;
  }--%>
          $.get(link, function(data) {
              $(update).html(data);
              $(update).show();
          });
          return false;
      }
      
      function semak(f,s)
      {
            
          var bil=0;var bila=0;var bilb=0;
          for (i = 0; i < f; i++){
              var c = document.getElementById("noLotBaru"+i);
              var lp = document.getElementById("luasPelanB1"+i);
              var nt = document.getElementById("nilaiTanah"+i);
              var d=c.value;
              if (c == null) break;
              if(d== ''){
                  bil++;
              }
              if(lp.value== ''){
                  bila++;
              }
              if(nt.value== ''){
                  bilb++;
              }
          }
          if(bil>0){
              alert("Sila Masukkan No Lot Baru")
              return false;
          }
          if(bila>0){
              alert("Sila Masukkan Luas Pelan B1")
              return false;
          }
          if(bilb>0){
              alert("Sila Masukkan Nilai Tanah")
              return false;
          }
          doSubmit(s.form, s.name, 'page_div')
      }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.rekodTuntutanLebihanActionBean">
    <s:messages/>
    <div  id="hakmilik_details">

        <div class="subtitle displaytag">

            <fieldset class="aras1">


                <c:if test="${edit}">
                    <legend>
                        Rekod Penerimaan Pampasan/Tuntutan Lebihan 
                    </legend>

                    <br>

                    <p align="center">  
                        <%--    <table align="left" >
                                   <tr>
                                       <td><label for="nolot">Nilaian Tanah :</label></td>
                                       <td><s:text name="nilaiTanah2" size="20" id="noLot"/></td>
                                       <td>&nbsp;</td>
                                   </tr>
                               </table>--%>
                        <br>
                        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
                            <%--  <div id="hakmiliks${line_rowNum}">--%>
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Hakmilik">
                                ${line.hakmilik.idHakmilik}
                            </display:column>
                            <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                            <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                            <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                            <display:column title="Luas Sebenar">
                                <s:hidden name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                                <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                                <fmt:formatNumber pattern="###0.0000" value="${line.hakmilik.luas}"/>
                                &nbsp;${line.hakmilik.kodUnitLuas.nama}
                            </display:column>
                            <display:column title="Luas Diambil">
                                <s:text name="luasTerlibat[${line_rowNum - 1}]" readonly="true" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})"/>
                                &nbsp;${line.hakmilik.kodUnitLuas.nama}
                            </display:column>
                            <display:column title="Baki Luas" >
                                <c:set value="${line.luasTerlibat}" var="a"/>
                                <c:set value="${line.hakmilik.luas}" var="c"/>
                                <fmt:formatNumber pattern="###0.0000" value="${c-a}"/>
                                &nbsp;${line.hakmilik.kodUnitLuas.nama}
                            </display:column>
                            <display:column title="Ukur Halus" >
                                <c:set value="${line.luasTerlibat}" var="a"/>
                                <c:set value="${line.luasPelanB1}" var="b"/>
                                <c:set value="${line.hakmilik.luas}" var="c"/>
                                <fmt:formatNumber pattern="###0.0000" value="${(c-a)-b}"/>
                                &nbsp;${line.hakmilik.kodUnitLuas.nama}
                            </display:column>
                            <display:column title="Luas Pelan B1">
                                <s:text name="luasPelanB1[${line_rowNum - 1}]" id="luasPelanB1${line_rowNum - 1}"/>
                                &nbsp;${line.hakmilik.kodUnitLuas.nama}
                            </display:column>
                            <display:column title="No Lot Baru">
                                <s:text size="8" name="noLotBaru[${line_rowNum - 1}]" id="noLotBaru${line_rowNum - 1}"/>

                                <%--<s:text name="nilaiTanah[${line_rowNum - 1}]" readonly="true" id="nilaiTanah${line_rowNum - 1}"/>--%>
                            </display:column>
                            <display:column title="Keluasan Tanah" >
                                &nbsp;${line.nomborRujukan}
                            </display:column>
                            <display:column title="Nilai Tanah (RM)">
                                <c:set value="${line_rowNum}" var="f"/>
                                <s:text name="nilaiTanah[${line_rowNum - 1}]" id="nilaiTanah${line_rowNum - 1}"/>
                                &nbsp;${line.hakmilik.kodUnitLuas.nama}

                            </display:column>
                            </div>
                        </display:table>
                        <br>
                        <s:button name="simpanHakmilik2" id="save" value="Simpan" class="btn" onclick="semak(${f},this)"/>

                    </c:if>
                    <c:if test="${!edit}">
                    <fieldset class="aras1">
                        <legend>
                            Maklumat Tanah
                        </legend>
                        <p align="center">
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                                           requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Hakmilik">
                                    <%--${line.hakmilik.idHakmilik}--%>
                                    ${line.hakmilik.idHakmilik}
                                </display:column>
                                <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                                <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                                <display:column title="Luas Sebenar">
                                    <s:hidden name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                                    <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                                    <fmt:formatNumber pattern="###0.0000" value="${line.hakmilik.luas}"/>
                                    &nbsp;${line.hakmilik.kodUnitLuas.nama}
                                </display:column>
                                <display:column title="Luas Diambil"><fmt:formatNumber  pattern="###0.0000" value="${line.luasTerlibat}" />
                                    <%--<s:text name="luasTerlibat[${line_rowNum - 1}]" readonly="true" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})"/>--%>
                                    &nbsp;${line.hakmilik.kodUnitLuas.nama}
                                </display:column>
                                <display:column title="Baki Luas">
                                    <c:set value="${line.luasTerlibat}" var="a"/>
                                    <c:set value="${line.hakmilik.luas}" var="c"/>
                                    <fmt:formatNumber pattern="###0.0000" value="${c-a}"/>
                                    &nbsp;${line.hakmilik.kodUnitLuas.nama}
                                </display:column>
                                <display:column title="Ukur Halus (Diambil)" >
                                    <c:set value="${line.luasTerlibat}" var="a"/>
                                    <c:set value="${line.luasPelanB1}" var="b"/>
                                    <c:set value="${line.hakmilik.luas}" var="c"/>
                                    <fmt:formatNumber pattern="###0.0000" value="${(c-a)-b}"/>
                                    &nbsp;${line.hakmilik.kodUnitLuas.nama}
                                </display:column>
                                <display:column title="Luas Pelan B1">
                                    <fmt:formatNumber pattern="###0.0000" value="${line.luasPelanB1}"/>
                                    <%--<s:text name="luasPelanB1[${line_rowNum - 1}]" readonly="true" id="luasPelanB1${line_rowNum - 1}"/>--%>
                                    &nbsp;${line.hakmilik.kodUnitLuas.nama}
                                </display:column>

                                <display:column title="Keluasan Tanah" >

                                    &nbsp;${line.nomborRujukan}
                                </display:column>
                                <display:column title="No Lot Baru">
                                    <fmt:formatNumber pattern="###0" value="${line.noLotBaru}" var="lb"/>

                                    <%--<s:text name="nilaiTanah[${line_rowNum - 1}]" readonly="true" id="nilaiTanah${line_rowNum - 1}"/>--%>
                                </display:column>
                                <display:column title="Nilai Tanah (RM)">
                                    <fmt:formatNumber pattern="###0.00" value="${line.nilaianJpph}" var="z"/>
                                    &nbsp;${line.hakmilik.kodUnitLuas.nama}
                                    <%--<s:text name="nilaiTanah[${line_rowNum - 1}]" readonly="true" id="nilaiTanah${line_rowNum - 1}"/>--%>
                                </display:column>

                            </display:table>
                    </fieldset>

                </c:if>
            </fieldset>
            K4 : Keluasan Tanah tidak lebih dari 1/4 Hektar<BR>
            L4 : Keluasan Tanah lebih dari 1/4 Hektar<BR>
            X4 : Sama dengan Borang K<BR>

        </div>
    </div>

</s:form>