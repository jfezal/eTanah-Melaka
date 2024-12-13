<%-- 
    Document   : carian_hakmilik_terperinci
    Created on : Mar 24, 2010, 9:59:53 AM
    Author     : khairil
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="etanah.model.Pengguna"%>
<html>
    <head><title>Carian Hakmilik</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <%
            Pengguna p = (Pengguna) request.getSession().getAttribute("_KEY_USER");
            String kodDaerah = p.getKodCawangan().getDaerah().getKod();
        %>
        <script type="text/javascript">
            function save(event, f){
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;

                $.post(url,q,
                function(data){
                    if(data == '0'){
                        alert("Tiada Hakmilik Dijumpai.Sila pastikan data - data hakmilik adalah tepat");
                    }else if(data == '1'){
                        alert("Sila Pilih Kod Bandar Pekan Mukim");
                    }else if(data == '2'){
                        alert("Sila Pilih Daerah");
                    }else if(data == '3'){
                        alert("Sila Pilih Jenis Lot");
                    }else if(data == '4'){
                        alert("Sila Masukkan No Lot / No PT");
                    }
                    else{
                        //$('#page_div',opener.document).html(data);
                        //opener.document.getElementById('test').value = data;
                        //self.close();
                        copyToTextBox(data);
                    }
           
                },'html');
            }
            function filterKodBPM(f){
                var kodDaerah = f
                //alert(kodDaerah);
                //var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/common/carian_hakmilik?senaraiKodBPMByDaerah&kodDaerah='+kodDaerah,
                function(data){
                    if(data != ''){
                        $('#partialKodBPM').html(data);
                    }
                }, 'html');
            }
            $(document).ready( function(){
                //filterKodBPM('05');
                //alert('test');
            <%--$("#namaDaerah").val($("#kodDaerah").val());

                $("#namaDaerah").change( function() {
                    var valueDaerah = $("#namaDaerah").val();
                    $("#kodDaerah").val(valueDaerah);
                });
                $("#kodDaerah").blur( function() {
                    var valueDaerah = $("#kodDaerah").val();
                    $("#namaDaerah").val(valueDaerah);
                });
                $("#kodBPM").blur( function() {
                    alert($("#namaBPM").val());
                    var valueBPM = $("#kodBPM").val();
                    $("#namaBPM").val(valueBPM);
                });
                $("#namaBPM").change( function() {
                    var valueBPM = $("#namaBPM").val();
                    $("#kodBPM").val(valueBPM);
                });
                $("#namaJenisHakmilik").change( function() {
                    var valueJenisHakmilik = $("#namaJenisHakmilik").val();
                    $("#kodJenisHakmilik").val(valueJenisHakmilik);
                });
                $("#kodJenisHakmilik").blur( function() {
                    var valueJenisHakmilik = $("#kodJenisHakmilik").val();
                    $("#namaJenisHakmilik").val(valueJenisHakmilik);
                });--%>

                    });
        </script>
    </head>
    <body>
        <s:messages/>
        <s:errors/>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
        <s:form beanclass="etanah.view.stripes.common.CarianHakmilik">

            <p>
                <label class="Label">Daerah </label>:
                <%--<s:text name="kodDaerah" size="4" id="kodDaerah" readonly="true" />
                ---%>
                <s:select name="daerah" id="namaDaerah" onchange="filterKodBPM(this.value);">
                    <%--<s:option value="">-- Sila Pilih --</s:option>--%>
                    <s:options-collection collection="${list.senaraiKodDaerah}" label="nama" value="kod"/>
                </s:select>
            </p>
            <div id="partialKodBPM">
                <p>
                    <label class="Label">Bandar / Pekan / Mukim </label>:
                    <%--<s:text name="kodBPM" size="4" id="kodBPM"/> ---%>
                    <s:select name="namaBPM" id="namaBPM">
                        <s:option value="">-- Sila Pilih --</s:option>
                        <s:options-collection collection="${actionBean.listBandarPekanMukim}" label="nama" value="bandarPekanMukim"/>
                    </s:select>

                </p>
            </div>
            <%-- <p>
                 <label>Jenis Hakmilik</label>
                 <s:text name="kodJenisHakmilik" size="4" id="kodJenisHakmilik"/> -
                 <s:select name="jenisHakmilik" id="namaJenisHakmilik">
                     <s:option value="">-- Sila Pilih --</s:option>
                     <s:options-collection collection="${list.senaraiKodHakmilik}" label="nama" value="kod" />
                 </s:select>
             </p>--%>
            <p><label class="Label">Kod Lot / No Lot </label>:
                <s:select name="jenisLot">

                    <s:options-collection collection="${list.senaraiKodLot}" label="nama" value="kod" />
                </s:select> /
                <s:text name="noLot"/>
            </p>
            <p>
                <label class="Label">&nbsp;</label>
                <s:button name="cari" value="Cari" class="btn" onclick="save(this.name,this.form);"/>&nbsp;
                <s:button name="tutup" value="Tutup" class="btn" onclick="closeDialog();"/>
            </p>
        </div>

    </s:form>
</body>
</html>